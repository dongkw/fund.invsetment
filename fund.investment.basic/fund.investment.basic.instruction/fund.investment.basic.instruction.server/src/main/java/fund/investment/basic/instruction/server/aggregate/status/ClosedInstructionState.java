package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.enumeration.InstructionStatus;

public class ClosedInstructionState extends InstructionState {

    public ClosedInstructionState() {
        super(InstructionStatus.CLOSED);
    }
}
