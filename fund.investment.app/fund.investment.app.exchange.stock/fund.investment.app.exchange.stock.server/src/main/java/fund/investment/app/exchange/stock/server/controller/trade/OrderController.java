package fund.investment.app.exchange.stock.server.controller.trade;

import fund.investment.app.exchange.stock.api.command.trade.ESCancelOrderCmd;
import fund.investment.app.exchange.stock.api.command.trade.ESCreateOrderCmd;
import fund.investment.app.exchange.stock.api.valueobject.ExchangeStockOrderTradeElement;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.command.CancelOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmOrderCmd;
import fund.investment.basic.trade.api.command.CreateOrderCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Api
@RestController
@RequestMapping("/investment/order")
public class OrderController {

    private final fund.investment.basic.common.util.uid.UIDGenerator UIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public OrderController(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.UIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    @ApiOperation(value = "创建委托")
    public ResponseEntity<CreateOrderCmd> create(
            @RequestParam String instructionId,
            @RequestParam String unitId,
            @RequestParam String accountId,
            @RequestParam String userId,
            @RequestBody ExchangeStockOrderTradeElement orderTradeElement
    ) {
        try {
            ESCreateOrderCmd cmd = new ESCreateOrderCmd();
            cmd.setId(UIDGenerator.getId());
            Object result = commandGateway.sendAndWait(cmd, 500, TimeUnit.SECONDS);
            Assert.notNull(result, "command dispatching timeout");

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (IllegalArgumentException illE) {
            illE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    @ApiOperation(value = "确认委托", tags = fund.investment.app.exchange.stock.server.controller.trade.SwaggerTag.TRADE)
    public ResponseEntity<ConfirmOrderCmd> confirm(
            @RequestParam String id,
            @RequestParam String instructionId,
            @RequestParam String tradeType
    ) {
        try {
            ConfirmOrderCmd cmd = new ConfirmOrderCmd(id,
                    tradeType,
                    instructionId,
                    null,
                    null,
                    null,
                    null);
            commandGateway.sendAndWait(cmd);
            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cancellation", method = RequestMethod.POST)
    @ApiOperation(value = "撤销报单", tags = fund.investment.app.exchange.stock.server.controller.trade.SwaggerTag.TRADE)
    public ResponseEntity<CancelOrderCmd> cancel(
            @RequestParam String id,
            @RequestParam String instructionId,
            @RequestParam String tradeType,
            @RequestParam String unitId,
            @RequestParam Long cancelQuantity
    ) {
        try {
            ESCancelOrderCmd cmd = new ESCancelOrderCmd();
            cmd.setId(id);
            cmd.setInstructionId(instructionId);
            cmd.setTradeType(tradeType);
            cmd.setUnitId(unitId);
            cmd.setCancelQuantity(cancelQuantity);
            commandGateway.send(cmd);

            return new ResponseEntity<>(cmd, HttpStatus.OK);
        } catch (CommandExecutionException cmdE) {
            cmdE.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
