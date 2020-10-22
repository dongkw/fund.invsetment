package fund.investment.distribution.domain.eventhandler;

import fund.investment.infrastructure.distribution.domain.command.DistributedIstrInitCmd;
import fund.investment.infrastructure.instruction.domain.model.event.IstrPassedEvt;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.infrastructure.util.uid.UIDGenerator;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DistEventHandler {

    @Autowired
    private UIDGenerator UIDGenerator;

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void on(IstrPassedEvt evt) {
        log.info(evt.toString());
        DistributedIstrInitCmd cmd = new DistributedIstrInitCmd();
        cmd.setId(UIDGenerator.getId());
        cmd.setInstructionId(evt.getId());
        commandGateway.send(cmd);
    }

}
