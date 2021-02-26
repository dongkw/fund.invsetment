package fund.investment.basic.instruction.server.service;

import fund.investment.basic.instruction.api.command.ApproveIstrCmd;
import fund.investment.gateway.api.approve.event.ApproveIstrEvt;
import fund.investment.gateway.api.approve.event.AprvIstrRejectedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApprovalHandler {

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void on(ApproveIstrEvt evt) {
        log.info("Recieved Event: {}", evt);
        ApproveIstrCmd approveIstrCmd = new ApproveIstrCmd();
        approveIstrCmd.setId(evt.getId());
        approveIstrCmd.setChFlowApproveStatus(evt.getChFlowApproveStatus());
        approveIstrCmd.setSkId(evt.getId());
        approveIstrCmd.setSkInstr(evt.getSkInstr());
        approveIstrCmd.setChLastModifiedId(evt.getChLastModifiedId());
        approveIstrCmd.setTsLastModifiedTime(evt.getTsLastModifiedTime());
        commandGateway.send(approveIstrCmd);
        log.info("Send command: {}", approveIstrCmd);
    }

    @EventHandler
    public void on(AprvIstrRejectedEvt evt) {
        //TODO 补充审批拒绝
        log.info("Recieved Event: {}", evt);
//        CancelIstrCmd cancelIstrCmd = new CancelIstrCmd();
//        cancelIstrCmd.setId(evt.getInstructionId());
//        cancelIstrCmd.setCancelMsg("审批拒绝");
//        cancelIstrCmd.setCancelType(EventType.APPROVAL_REJECT.getCode());
//        cancelIstrCmd.setLastmodifiedId(evt.getOperatorId());
//        cancelIstrCmd.setLastmodifiedTime(evt.getOperatorTime());
//        commandGateway.send(cancelIstrCmd);
//        log.info("Send command: {}", cancelIstrCmd);
    }

}

