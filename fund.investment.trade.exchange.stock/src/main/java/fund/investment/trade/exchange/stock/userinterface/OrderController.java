package fund.investment.trade.exchange.stock.userinterface;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fund.investment.infrastructure.util.SwaggerTag;
import fund.investment.infrastructure.util.uid.UIDGenerator;
import infrastructure.trade.domain.model.command.CancelOrderCmd;
import infrastructure.trade.domain.model.command.ConfirmOrderCmd;
import infrastructure.trade.domain.model.command.CreateOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.command.ESCancelOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.command.ESCreateOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/investment/order")
public class OrderController {
	
	@Autowired
	private UIDGenerator UIDGenerator ;
	
	@Autowired
    private CommandGateway commandGateway;
	
	@RequestMapping(value = "/creation", method = RequestMethod.POST)
	@ApiOperation(value = "创建委托", tags = SwaggerTag.TRADE)
	public ResponseEntity<CreateOrderCmd> create(
			@RequestParam String instructionId, 
			@RequestParam String unitId, 
			@RequestParam String accountId,
			@RequestParam String userId,
			@RequestBody ExchangeStockOrderTradeElement orderTradeElement
			) {
		try {
			ESCreateOrderCmd cmd = new ESCreateOrderCmd(
					UIDGenerator.getId(), 
					instructionId, 
					orderTradeElement.getTradeType(), 
					unitId, 
					accountId, 
					userId, 
					orderTradeElement,
					BigDecimal.ZERO, 
					0l, 
					BigDecimal.ZERO, 
					0l, 
					BigDecimal.ZERO); 
			Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);
			Assert.notNull(result, "command dispatching timeout");
			
			return new ResponseEntity<>(cmd, HttpStatus.OK);
		} catch (IllegalArgumentException illE) {
			illE.printStackTrace();
			return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
			
		} catch (CommandExecutionException cmdE) {
			cmdE.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@RequestMapping(value = "/confirmation", method = RequestMethod.POST)
	@ApiOperation(value = "确认委托", tags = SwaggerTag.TRADE)
	public ResponseEntity<ConfirmOrderCmd> confirm(
			@RequestParam String id, 
			@RequestParam String instructionId,
			@RequestParam String tradeType
			) {
		try {
			ConfirmOrderCmd cmd = new ConfirmOrderCmd(id, instructionId, tradeType);
			commandGateway.sendAndWait(cmd);
			return new ResponseEntity<>(cmd, HttpStatus.OK);
		} catch (CommandExecutionException cmdE) {
			cmdE.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/cancellation", method = RequestMethod.POST)
	@ApiOperation(value = "撤销报单", tags = SwaggerTag.TRADE)
	public ResponseEntity<CancelOrderCmd> cancel(
			@RequestParam String id, 
			@RequestParam String instructionId,
			@RequestParam String tradeType,
			@RequestParam String unitId,
			@RequestParam Long cancelQuantity
			
			) {
		try {
			ESCancelOrderCmd cmd = new ESCancelOrderCmd(id, instructionId, tradeType, unitId, cancelQuantity);
			commandGateway.send(cmd);
			
			return new ResponseEntity<>(cmd, HttpStatus.OK);
		} catch (CommandExecutionException cmdE) {
			cmdE.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
