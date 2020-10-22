package fund.investment.instruction.domain.model.eventhandler;

import fund.investment.infrastructure.approve.domain.event.AprvIstrPassEvt;
import fund.investment.infrastructure.approve.domain.event.AprvIstrRejectedEvt;
import fund.investment.infrastructure.instruction.domain.model.command.AprvPassIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrCmd;
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
    public void on(AprvIstrPassEvt evt) {
        log.info("Recieved Event: {}", evt);
        AprvPassIstrCmd aprvPassIstrCmd = new AprvPassIstrCmd();
        aprvPassIstrCmd.setId(evt.getInstructionId());
//        aprvPassIstrCmd.setApproveStatus(ApprovalStatus.PASS);
        commandGateway.send(aprvPassIstrCmd);
        log.info("Send command: {}", aprvPassIstrCmd);
    }

    @EventHandler
    public void on(AprvIstrRejectedEvt evt) {
        log.info("Recieved Event: {}", evt);
        CancelIstrCmd cancelIstrCmd = new CancelIstrCmd();
        cancelIstrCmd.setId(evt.getInstructionId());
        cancelIstrCmd.setCancelMsg("审批拒绝");
        commandGateway.send(cancelIstrCmd);
        log.info("Send command: {}", cancelIstrCmd);
    }

}

