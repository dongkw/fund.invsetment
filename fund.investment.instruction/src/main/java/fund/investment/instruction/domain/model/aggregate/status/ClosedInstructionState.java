package fund.investment.instruction.domain.model.aggregate.status;


import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;

public class ClosedInstructionState extends InstructionState {
	public ClosedInstructionState() {
		super(InstructionStatus.CLOSED);
	}
}
