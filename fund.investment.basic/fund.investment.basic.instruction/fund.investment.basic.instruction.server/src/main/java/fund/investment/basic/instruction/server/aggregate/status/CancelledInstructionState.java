package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.valueobject.TradeElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancelledInstructionState<T extends TradeElement> extends InstructionState<T> {

    public CancelledInstructionState() {
        super(InstructionStatus.CANCELLED);
    }
}
