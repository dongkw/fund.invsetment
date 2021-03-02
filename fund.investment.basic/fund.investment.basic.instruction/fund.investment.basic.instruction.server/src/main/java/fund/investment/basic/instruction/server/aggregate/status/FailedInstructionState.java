package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.valueobject.TradeElement;

public class FailedInstructionState<T extends TradeElement> extends InstructionState<T> {

    public FailedInstructionState() {
        super(InstructionStatus.FAILED);
    }
}
