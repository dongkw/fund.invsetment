package fund.investment.app.pledge.repo.server.controller.instruction;

import fund.investment.app.pledge.repo.api.command.instruction.PRCancelIstrCmd;
import fund.investment.app.pledge.repo.api.command.instruction.PRCreateIstrCmd;
import fund.investment.app.pledge.repo.api.command.instruction.PRUpdateIstrCmd;
import fund.investment.app.pledge.repo.api.event.instruction.PRIstrUpdateConfirmedEvt;
import fund.investment.app.pledge.repo.api.event.instruction.PRIstrUpdatedFailedEvt;
import fund.investment.basic.common.http.response.pledgerepo.PRInstructionTransmitResponse;
import fund.investment.basic.common.http.response.pledgerepo.ResultEntity;
import fund.investment.basic.common.http.response.pledgerepo.TradeInvestResponse;
import fund.investment.basic.common.util.controller.Async2SyncController;
import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.instruction.api.event.IstrCancelledEvt;
import fund.investment.basic.instruction.api.event.IstrConfirmedEvt;
import fund.investment.basic.instruction.api.event.IstrFailedEvt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync/bank-investment/inner-bank")
@Api(tags = "Sync Exchange Instruction")
public class PRInstructionSyncController extends Async2SyncController {
    @Autowired
    private UIDGenerator uidGenerator;
    @Autowired
    private PRInstructionAsyncController prInstructionAsyncController;

    @RequestMapping(value = "/instruction/transmit", method = RequestMethod.POST)
    @ApiOperation(value = "下达指令")
    public ResponseEntity<ResultEntity> createAndConfirm(@RequestBody PRCreateIstrCmd cmd) {
        try {
            cmd.setRequestId(uidGenerator.nextId());

            prInstructionAsyncController.create(cmd);
            return waitResponse(cmd.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @EventHandler
    public void on(IstrConfirmedEvt evt) {
        PRInstructionTransmitResponse response = new PRInstructionTransmitResponse();
        setStatus(response);
        response.setInvest(new TradeInvestResponse());
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void on(IstrFailedEvt evt) {
        PRInstructionTransmitResponse response = new PRInstructionTransmitResponse();
        //setStatus(response);
        response.setCommandStatus(Boolean.TRUE);
        response.setComplianceStatus(Boolean.FALSE);
        TradeInvestResponse tradeInvestResponse = new TradeInvestResponse();
        tradeInvestResponse.setRiskInfos(evt.getRiskRiskInfos());
        response.setInvest(tradeInvestResponse);
        addResponse(evt.getRequestId(), response);
    }



    @RequestMapping(value = "/instruction/modification", method = RequestMethod.POST)
    @ApiOperation(value = "修改指令")
    public ResponseEntity<ResultEntity> modify(@RequestBody PRUpdateIstrCmd cmd) {
        try {
            cmd.setRequestId(uidGenerator.nextId());
            prInstructionAsyncController.update(cmd);
            return waitResponse(cmd.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/instruction/cancellation", method = RequestMethod.POST)
    @ApiOperation(value = "撤销指令")
    public ResponseEntity<ResultEntity> cancel(@RequestBody PRCancelIstrCmd cmd) {
        cmd.setRequestId(uidGenerator.nextId());
        prInstructionAsyncController.cancel(cmd);
        return waitResponse(cmd.getRequestId());
    }




    @EventHandler
    public void on(IstrCancelledEvt evt) {
        ResultEntity response = new ResultEntity();
        setStatus(response);
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void on(PRIstrUpdateConfirmedEvt evt) {
        ResultEntity response = new ResultEntity();
        setStatus(response);
        addResponse(evt.getRequestId(), response);
    }

    @EventHandler
    public void on(PRIstrUpdatedFailedEvt evt) {
//        ResultEntity response = new ResultEntity();
//        setStatus(response);
//        addResponse(evt.getRequestId(), response);
        PRInstructionTransmitResponse response = new PRInstructionTransmitResponse();
        //setStatus(response);
        response.setCommandStatus(Boolean.TRUE);
        response.setComplianceStatus(Boolean.FALSE);
        TradeInvestResponse tradeInvestResponse = new TradeInvestResponse();
        tradeInvestResponse.setRiskInfos(evt.getRiskRiskInfos());
        response.setInvest(tradeInvestResponse);
        addResponse(evt.getRequestId(), response);
    }

    private void setStatus(ResultEntity response) {
        response.setCommandStatus(Boolean.TRUE);
        response.setComplianceStatus(Boolean.TRUE);
    }
}