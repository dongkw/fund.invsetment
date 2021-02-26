package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.enumeration.InstructionStatus;

public class CompletedInstructionState extends InstructionState {

    public CompletedInstructionState() {
        super(InstructionStatus.COMPLETED);
    }
}
