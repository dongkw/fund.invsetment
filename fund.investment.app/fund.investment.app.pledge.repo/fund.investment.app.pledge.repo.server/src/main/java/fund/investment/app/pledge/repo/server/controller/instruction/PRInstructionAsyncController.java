package fund.investment.app.pledge.repo.server.controller.instruction;

import fund.investment.app.pledge.repo.api.command.instruction.PRCancelIstrCmd;
import fund.investment.app.pledge.repo.api.command.instruction.PRCreateIstrCmd;
import fund.investment.app.pledge.repo.api.command.instruction.PRUpdateIstrCmd;
import fund.investment.basic.common.http.response.pledgerepo.PRInstructionTransmitResponse;
import fund.investment.basic.common.http.response.pledgerepo.ResultEntity;
import fund.investment.basic.common.http.response.pledgerepo.TradeInvestResponse;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.instruction.api.command.CreateConfirmIstrCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/async/bank-investment/inner-bank")
@Api(tags = "Async Exchange Instruction")
public class PRInstructionAsyncController {

    private final CommandGateway commandGateway;

    private final UIDGenerator uidGenerator;

    @Autowired
    public PRInstructionAsyncController(CommandGateway commandGateway, UIDGenerator uidGenerator) {
        this.commandGateway = commandGateway;
        this.uidGenerator = uidGenerator;
    }

    @RequestMapping(value = "/instruction/addition", method = RequestMethod.POST)
    @ApiOperation(value = "创建指令")
    public ResponseEntity<PRInstructionTransmitResponse> create(@RequestBody PRCreateIstrCmd cmd) {
        try {
            if(Objects.isNull(cmd) || StringUtils.isEmpty(cmd.getSkInstr())){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            cmd.setId(cmd.getSkInstr());
            commandGateway.sendAndWait(cmd);
            PRInstructionTransmitResponse response = new PRInstructionTransmitResponse();
            response.setCommandStatus(Boolean.TRUE);
            TradeInvestResponse invest = new TradeInvestResponse();
            invest.setCInstrSource("cmd.getcInstrSource()");
            invest.setCSourceKey("cmd.getcSourceKey()");
            invest.setCSourceNo("cmd.getcSourceNo()");
            response.setInvest(invest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/instruction/confirmation", method = RequestMethod.POST)
    @ApiOperation(value = "确认指令")
    public ResponseEntity<ResultEntity> confirm(@RequestBody String id) {
        try {
            CreateConfirmIstrCmd instruction = new CreateConfirmIstrCmd();
            instruction.setId(id);
            commandGateway.sendAndWait(instruction);
            return ResponseEntity.ok(new ResultEntity(Boolean.TRUE));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/instruction/cancellation", method = RequestMethod.POST)
    @ApiOperation(value = "撤销指令")
    public ResponseEntity<ResultEntity> cancel(@RequestBody PRCancelIstrCmd cmd) {
        try {
            commandGateway.sendAndWait(cmd);
            return ResponseEntity.ok(new ResultEntity(Boolean.TRUE));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/instruction/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改指令")
    public ResponseEntity<ResultEntity> update(@RequestBody PRUpdateIstrCmd cmd) {
        try {
            if(Objects.isNull(cmd) || StringUtils.isEmpty(cmd.getSkInstr())){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            cmd.setId(cmd.getSkInstr());
            commandGateway.sendAndWait(cmd);
            PRInstructionTransmitResponse response = new PRInstructionTransmitResponse();
            response.setCommandStatus(Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}