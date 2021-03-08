package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import fund.investment.basic.instruction.server.aggregate.InstructionState;

public class FailedInstructionState<T extends InstructionElement> extends InstructionState<T> {

    public FailedInstructionState() {
        super(InstructionStatus.FAILED);
    }
}
