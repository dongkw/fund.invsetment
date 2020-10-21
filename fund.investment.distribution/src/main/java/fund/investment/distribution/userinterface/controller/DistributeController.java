package fund.investment.distribution.userinterface.controller;

import fund.investment.infrastructure.distribution.domain.command.DistIstrRejectedCmd;
import fund.investment.infrastructure.distribution.domain.command.DistributedIstrPassCmd;
import fund.investment.infrastructure.distribution.domain.constant.DistSwaggerTag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/investment/distribution")
public class DistributeController {
	
	@Autowired
    private CommandGateway commandGateway;

	@RequestMapping(value = "/pass", method = RequestMethod.GET)
	@ApiOperation(value = "已分发", tags = DistSwaggerTag.INSTRUCTION_DISTRIBUTION)
	public ResponseEntity<DistributedIstrPassCmd> pass(
										@RequestParam String id,
										@RequestParam String instructionId,
                                        @RequestParam String userId,
                                        @RequestParam String operatorId) {
		try {
			DistributedIstrPassCmd instruction = DistributedIstrPassCmd.builder().id(id).instructionId(instructionId).userId(userId).operatorId(operatorId).build();
			commandGateway.sendAndWait(instruction);
			return new ResponseEntity<>(instruction, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/refuse", method = RequestMethod.GET)
	@ApiOperation(value = "拒绝分发", tags = DistSwaggerTag.INSTRUCTION_DISTRIBUTION)
	public ResponseEntity<DistIstrRejectedCmd> refuse(
			@RequestParam String id,
			@RequestParam String instructionId,
			@RequestParam String userId,
			@RequestParam String operatorId) {
		try {
			DistIstrRejectedCmd instruction = DistIstrRejectedCmd.builder().id(id).instructionId(instructionId).userId(userId).operatorId(operatorId).build();
			commandGateway.sendAndWait(instruction);
			return new ResponseEntity<>(instruction, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
