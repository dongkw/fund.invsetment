package fund.investment.instruction.domain.model.eventhandler;

import fund.investment.infrastructure.distribution.domain.event.DistIstrRejectedEvt;
import fund.investment.infrastructure.distribution.domain.event.DistributedIstrEvt;
import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.command.DistributeIstrCmd;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DistributeHandler {
    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void on(DistributedIstrEvt evt) {
        log.info("Recieved Event: {}", evt);
        DistributeIstrCmd distributeIstrCmd = new DistributeIstrCmd();
        distributeIstrCmd.setId(evt.getInstructionId());
        commandGateway.send(distributeIstrCmd);
        log.info("Send command: {}", distributeIstrCmd);
    }

    @EventHandler
    public void on(DistIstrRejectedEvt evt) {
        log.info("Recieved Event: {}", evt);
        CancelIstrCmd cancelIstrCmd = new CancelIstrCmd();
        cancelIstrCmd.setId(evt.getInstructionId());
        cancelIstrCmd.setCancelMsg("分发拒绝");
        commandGateway.send(cancelIstrCmd);
        log.info("Send command: {}", cancelIstrCmd);
    }

}

