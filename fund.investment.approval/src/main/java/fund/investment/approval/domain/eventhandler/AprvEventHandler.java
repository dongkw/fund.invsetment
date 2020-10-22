package fund.investment.approval.domain.eventhandler;

import fund.investment.infrastructure.approve.domain.command.AprvIstrInitializationCmd;
import fund.investment.infrastructure.instruction.domain.model.event.IstrConfirmedEvt;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.infrastructure.util.uid.UIDGenerator;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AprvEventHandler {
	
	@Autowired
    private CommandGateway commandGateway;

	@Autowired
	private CommandBus commandBus;
	
	@Autowired
	private UIDGenerator UIDGenerator;
	
	@EventHandler
	public void on(IstrConfirmedEvt evt) {
		log.info(evt.toString());
		AprvIstrInitializationCmd cmd = AprvIstrInitializationCmd.builder().id(UIDGenerator.getId()).instructionId(evt.getId()).build();
		commandGateway.send(cmd);
	}
	
}
