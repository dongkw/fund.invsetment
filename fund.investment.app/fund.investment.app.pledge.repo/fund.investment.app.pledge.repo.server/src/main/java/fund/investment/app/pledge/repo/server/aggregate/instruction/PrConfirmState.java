package fund.investment.app.pledge.repo.server.aggregate.instruction;

import fund.investment.app.pledge.repo.api.event.instruction.PRIstrCancellingEvt;
import fund.investment.app.pledge.repo.api.event.instruction.PRIstrUpdatedEvt;
import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeTradeElement;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.command.UpdateIstrCmd;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.status.ConfirmedInstructionState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/2、9:45 上午
 **/
public class PrConfirmState<T extends PledgeTradeElement> extends ConfirmedInstructionState<T> {

    @Override
    public void update(InstructionAggregate<T> aggregate, UpdateIstrCmd<T> cmd) {
        PRIstrUpdatedEvt evt = new PRIstrUpdatedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void cancel(InstructionAggregate<T> aggregate, CancelIstrCmd cancelIstrCmd) {
        PRIstrCancellingEvt istrCancellingEvt = new PRIstrCancellingEvt();
        istrCancellingEvt.copyOf(cancelIstrCmd);
        istrCancellingEvt.setRiskInfos(cancelIstrCmd.getRiskInfos());
        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
