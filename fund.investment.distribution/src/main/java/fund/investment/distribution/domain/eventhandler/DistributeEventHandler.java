package fund.investment.distribution.domain.eventhandler;

import fund.investment.infrastructure.distribution.domain.command.DistributedIstrInitCmd;
import fund.investment.infrastructure.instruction.domain.model.event.IstrPassedEvt;
import fund.investment.infrastructure.util.uid.UIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DistributeEventHandler {

    private final UIDGenerator UIDGenerator;

    private final CommandGateway commandGateway;

    @Autowired
    public DistributeEventHandler(UIDGenerator UIDGenerator, CommandGateway commandGateway) {
        this.UIDGenerator = UIDGenerator;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(IstrPassedEvt evt) {
        log.info(evt.toString());
        DistributedIstrInitCmd cmd = new DistributedIstrInitCmd();
        cmd.setId(UIDGenerator.getId());
        cmd.setInstructionId(evt.getId());
        commandGateway.send(cmd);
    }
}
