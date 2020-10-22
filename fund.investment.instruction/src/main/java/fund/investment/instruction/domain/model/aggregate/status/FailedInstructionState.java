package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;

public class FailedInstructionState extends InstructionState {

    public FailedInstructionState() {
        super(InstructionStatus.FAILED);
    }
}
