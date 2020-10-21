package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.command.DistributeIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrPendingEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PassedInstructionState extends CancelableInstructionState {
	
	public PassedInstructionState() {
		super(InstructionStatus.APRV_PASS);
	}

    @Override
    public void distribute(DistributeIstrCmd distributeIstrCmd) {
        log.info("[PassedInstructionState] Receive command: {}", distributeIstrCmd);
	    IstrPendingEvt istrPendingEvt = new IstrPendingEvt();
        istrPendingEvt.setId(distributeIstrCmd.getId());
        AggregateLifecycle.apply(istrPendingEvt);
        log.info("[PassedInstructionState] Dispached Event: {}", istrPendingEvt);
    }
}
