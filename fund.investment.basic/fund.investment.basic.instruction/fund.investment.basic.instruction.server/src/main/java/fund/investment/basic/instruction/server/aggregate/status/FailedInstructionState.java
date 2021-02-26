package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.enumeration.InstructionStatus;

public class FailedInstructionState extends InstructionState {

    public FailedInstructionState() {
        super(InstructionStatus.FAILED);
    }
}
