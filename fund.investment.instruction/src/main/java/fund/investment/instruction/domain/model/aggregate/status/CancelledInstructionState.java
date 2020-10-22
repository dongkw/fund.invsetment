package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancelledInstructionState extends InstructionState {

    public CancelledInstructionState() {
        super(InstructionStatus.CANCELLED);
    }
}
