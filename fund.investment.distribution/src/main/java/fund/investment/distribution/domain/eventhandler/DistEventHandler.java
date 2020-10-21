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

//	@Autowired
//	private CommandBus commandBus;
	
	@EventHandler
	public void on(IstrPassedEvt evt) {
		log.info(evt.toString());
		DistributedIstrInitCmd cmd = DistributedIstrInitCmd.builder().id(UIDGenerator.getId()).instructionId(evt.getId()).build();
		commandGateway.send(cmd);
//		commandBus.dispatch(new GenericCommandMessage<>(cmd));
	}
	
}
