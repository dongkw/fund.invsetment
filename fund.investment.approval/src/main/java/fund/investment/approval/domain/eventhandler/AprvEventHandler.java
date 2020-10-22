package fund.investment.approval.domain.eventhandler;

import fund.investment.infrastructure.approve.domain.command.AprvIstrInitializationCmd;
import fund.investment.infrastructure.instruction.domain.model.event.IstrConfirmedEvt;
import fund.investment.infrastructure.util.uid.UIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AprvEventHandler {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private UIDGenerator UIDGenerator;

    @EventHandler
    public void on(IstrConfirmedEvt evt) {
        log.info(evt.toString());
        AprvIstrInitializationCmd cmd = new AprvIstrInitializationCmd();
        cmd.setId(UIDGenerator.getId());
        cmd.setInstructionId(evt.getId());
        commandGateway.send(cmd);
    }

}
