package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.api.command.ApproveIstrCmd;
import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.command.UpdateIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrCancellingEvt;
import fund.investment.basic.instruction.api.event.IstrPassedEvt;
import fund.investment.basic.instruction.api.event.IstrUpdatedEvt;
import fund.investment.basic.instruction.api.valueobject.TradeElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class ConfirmedInstructionState<T extends TradeElement> extends InstructionState<T> {

    public ConfirmedInstructionState() {
        super(InstructionStatus.CONFIRMED);
    }

    @Override
    public void aprvPass(InstructionAggregate<T> instructionAggregate, ApproveIstrCmd approveIstrCmd) {
        IstrPassedEvt istrPassedEvt = new IstrPassedEvt();
        istrPassedEvt.copyOf(approveIstrCmd);
        AggregateLifecycle.apply(istrPassedEvt);
    }

    @Override
    public void update(InstructionAggregate<T> aggregate, UpdateIstrCmd<T> cmd) {
        IstrUpdatedEvt<T> evt = new IstrUpdatedEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void cancel(InstructionAggregate<T> aggregate, CancelIstrCmd cancelIstrCmd) {
        IstrCancellingEvt istrCancellingEvt = new IstrCancellingEvt();
        istrCancellingEvt.copyOf(cancelIstrCmd);
        istrCancellingEvt.setRiskInfos(cancelIstrCmd.getRiskInfos());
        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
