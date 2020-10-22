package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.command.AprvPassIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrPassedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class ConfirmedInstructionState extends CancelableInstructionState  {
	
	public ConfirmedInstructionState() {
		super(InstructionStatus.CONFIRMED);
	}

    @Override
    public void aprvPass(AprvPassIstrCmd aprvPassIstrCmd) {
        log.info("Receive command: {}", aprvPassIstrCmd);
        IstrPassedEvt istrPassedEvt = IstrPassedEvt.builder().build();
        istrPassedEvt.setId(aprvPassIstrCmd.getId());
        AggregateLifecycle.apply(istrPassedEvt);
        log.info("Dispached Event: {}", istrPassedEvt);
    }

}
