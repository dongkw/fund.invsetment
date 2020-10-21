package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCancellingEvt;
import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import fund.investment.instruction.exchange.stock.domain.model.event.ESIstrCancellingEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CancelableInstructionState extends InstructionState {

    public CancelableInstructionState(InstructionStatus instructionStatus) {
        super(instructionStatus);
    }

    @Override
    public void cancel(InstructionAggregate aggregate, CancelIstrCmd cancelIstrCmd) {
        log.info("[CancelableInstructionState] Receive command: {}", cancelIstrCmd);
        ESIstrCancellingEvt istrCancellingEvt = new ESIstrCancellingEvt();
//		istrCancellingEvt.setOrders(cancelIstrCmd.getOrders());
        istrCancellingEvt.setId(cancelIstrCmd.getId());
        istrCancellingEvt.setUnitId(aggregate.getUnitId());
        istrCancellingEvt.setSecurityCode(aggregate.getIstrTradeElement().getSecurityCode());
        AggregateLifecycle.apply(istrCancellingEvt);
        log.info("[CancellingInstructionState] Dispached Event: {}", istrCancellingEvt);
    }

}
