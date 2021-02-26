package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancelledInstructionState extends InstructionState {

    public CancelledInstructionState() {
        super(InstructionStatus.CANCELLING);
    }
}
