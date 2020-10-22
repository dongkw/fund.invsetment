package fund.investment.approval.userinterface.controller;

import fund.investment.infrastructure.approve.domain.command.AprvIstrPassCmd;
import fund.investment.infrastructure.approve.domain.command.AprvIstrRejectedCmd;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@Slf4j
@RestController
@RequestMapping("/investment/approval")
public class AprvController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/pass", method = RequestMethod.GET)
    public ResponseEntity<AprvIstrPassCmd> pass(
            @RequestParam String id,
            @RequestParam String instructionId,
            @RequestParam String userId,
            @RequestParam String operatorId) {
        try {
            AprvIstrPassCmd instruction = new AprvIstrPassCmd(id, instructionId, userId, operatorId);
            commandGateway.sendAndWait(instruction);
            return new ResponseEntity<>(instruction, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(value = "/refuse", method = RequestMethod.GET)
    public ResponseEntity<AprvIstrRejectedCmd> refuse(
            @RequestParam String id,
            @RequestParam String instructionId,
            @RequestParam String userId,
            @RequestParam String operatorId) {
        try {
            AprvIstrRejectedCmd instruction = new AprvIstrRejectedCmd(id, instructionId, userId, operatorId);
            commandGateway.sendAndWait(instruction);
            return new ResponseEntity<>(instruction, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
