package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrCancellingEvt;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CancelableInstructionState extends InstructionState {

    public CancelableInstructionState(InstructionStatus instructionStatus) {
        super(instructionStatus);
    }

    @Override
    public void cancel(InstructionAggregate aggregate, CancelIstrCmd cancelIstrCmd) {
        IstrCancellingEvt istrCancellingEvt = new IstrCancellingEvt();
        istrCancellingEvt.setTradeType(cancelIstrCmd.getTradeType());
        istrCancellingEvt.setId(cancelIstrCmd.getId());
        istrCancellingEvt.setUnitId(aggregate.getUnitId());
        istrCancellingEvt.setOrders(cancelIstrCmd.getOrders());
        istrCancellingEvt.setCancelType(cancelIstrCmd.getCancelType());
        istrCancellingEvt.setCancelMsg(cancelIstrCmd.getCancelMsg());
        istrCancellingEvt.setSkId(aggregate.getSkId());
        istrCancellingEvt.setSkInstr(aggregate.getSkInstr());
        istrCancellingEvt.setChLastModifiedId(cancelIstrCmd.getChLastModifiedId());
        istrCancellingEvt.setTsLastModifiedTime(cancelIstrCmd.getTsLastModifiedTime());
        istrCancellingEvt.setRequestId(cancelIstrCmd.getRequestId());
        istrCancellingEvt.setChInstrSource(cancelIstrCmd.getChInstrSource());
        istrCancellingEvt.setChSourceKey(cancelIstrCmd.getChSourceKey());
        istrCancellingEvt.setChSourceNo(cancelIstrCmd.getChSourceNo());
        istrCancellingEvt.setUserId(cancelIstrCmd.getUserId());
        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
