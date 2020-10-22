package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.command.CreateConfirmIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.command.CreateFailIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrConfirmedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrFailedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CreatedInstructionState extends InstructionState {
	
	public CreatedInstructionState() {
		super(InstructionStatus.CREATED);
	}
	
    @Override
    public void createConfirm(CreateConfirmIstrCmd createConfirmIstrCmd) {
        log.info("Receive command: {}", createConfirmIstrCmd);
	    IstrConfirmedEvt istrConfirmedEvt = new IstrConfirmedEvt();
	    istrConfirmedEvt.setId(createConfirmIstrCmd.getId());
	    istrConfirmedEvt.setTradeType(createConfirmIstrCmd.getTradeType());
        AggregateLifecycle.apply(istrConfirmedEvt);
        log.info("Dispached Event: {}", istrConfirmedEvt);
    }

    @Override
    public void createFail(CreateFailIstrCmd createFailIstrCmd) {
        log.info("Receive command: {}", createFailIstrCmd);
        IstrFailedEvt istrFailedEvt = new IstrFailedEvt();
        istrFailedEvt.setId(createFailIstrCmd.getId());
        istrFailedEvt.setFailCode(createFailIstrCmd.getFailCode());
	    istrFailedEvt.setFailMsg(createFailIstrCmd.getFailMsg());
        AggregateLifecycle.apply(istrFailedEvt);
        log.info("Dispached Event: {}", istrFailedEvt);
    }
}
