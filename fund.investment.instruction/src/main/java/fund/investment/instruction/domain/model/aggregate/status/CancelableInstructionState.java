package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCancellingEvt;
import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CancelableInstructionState extends InstructionState {

    public CancelableInstructionState(InstructionStatus instructionStatus) {
        super(instructionStatus);
    }

    @Override
    public void cancel(InstructionAggregate aggregate, CancelIstrCmd cancelIstrCmd) {
        log.info("Receive command: {}", cancelIstrCmd);
        IstrCancellingEvt istrCancellingEvt = new IstrCancellingEvt(cancelIstrCmd.getTradeType(),cancelIstrCmd.getId(),
                aggregate.getUnitId(),aggregate.getIstrTradeElement().getSecurityCode(),cancelIstrCmd.getOrders());
        AggregateLifecycle.apply(istrCancellingEvt);
        log.info("Dispached Event: {}", istrCancellingEvt);
    }

}
