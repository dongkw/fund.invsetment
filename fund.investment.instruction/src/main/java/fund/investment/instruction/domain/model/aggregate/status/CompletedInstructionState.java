package fund.investment.instruction.domain.model.aggregate.status;


import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;

public class CompletedInstructionState extends InstructionState {
	
	public CompletedInstructionState() {
		super(InstructionStatus.COMPLETED);
	}
}
