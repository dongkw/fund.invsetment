package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.valueobject.TradeElement;

public class CompletedInstructionState<T extends TradeElement> extends InstructionState<T> {

    public CompletedInstructionState() {
        super(InstructionStatus.COMPLETED);
    }
}
