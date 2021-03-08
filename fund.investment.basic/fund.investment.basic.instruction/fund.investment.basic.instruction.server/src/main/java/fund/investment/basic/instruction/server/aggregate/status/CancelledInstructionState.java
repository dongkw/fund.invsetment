package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import fund.investment.basic.instruction.server.aggregate.InstructionState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancelledInstructionState<T extends InstructionElement> extends InstructionState<T> {

    public CancelledInstructionState() {
        super(InstructionStatus.CANCELLED);
    }
}
