package fund.investment.exchange.stock.userinterface.controller;

import fund.investment.exchange.stock.config.InstructionTag;
import fund.investment.infrastructure.instruction.domain.model.command.CreateConfirmIstrCmd;
import fund.investment.infrastructure.util.uid.UIDGenerator;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrOrderCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/bank-investment/exchange")
public class InstructionController {
	
	@Autowired
    private CommandGateway commandGateway;
	
	@Autowired
	private QueryGateway queryGateway;

	@Autowired
	private UIDGenerator uidGenerator;
	
	@RequestMapping(value = "/instruction/addition", method = RequestMethod.POST)
    @ApiOperation(value = "创建指令", tags = InstructionTag.EXCHANGE_INSTRUCTION)
    public ResponseEntity<String> create(@RequestBody ESCreateIstrCmd cmd) {
		try {
			String id = uidGenerator.getId()+"";
			cmd.setId(id);
			commandGateway.sendAndWait(cmd);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/instruction/createOrder", method = RequestMethod.POST)
	@ApiOperation(value = "委托校验指令", tags = InstructionTag.EXCHANGE_INSTRUCTION)
	public ResponseEntity<Void> createOrder(@RequestBody ESCreateIstrOrderCmd cmd) {
		try {
			commandGateway.sendAndWait(cmd);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@RequestMapping(value = "/instruction/confirmation", method = RequestMethod.POST)
	@ApiOperation(value = "确认指令", tags = InstructionTag.EXCHANGE_INSTRUCTION)
	public ResponseEntity<Void> confirm(@RequestBody String id) {
		try {
			CreateConfirmIstrCmd instruction = CreateConfirmIstrCmd.builder().build();
			instruction.setId(id);
			commandGateway.sendAndWait(instruction);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/instruction/cancellation", method = RequestMethod.POST)
	@ApiOperation(value = "撤销指令", tags = InstructionTag.EXCHANGE_INSTRUCTION)
	public ResponseEntity<Void> cancel(@RequestBody String id) {
		try {
			ESCancelIstrCmd cmd = new ESCancelIstrCmd();
			cmd.setId(id);
			cmd.setCancelMsg("取消");
			commandGateway.sendAndWait(cmd);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
