package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.AutoCancelMatchOrderCmd;
import fund.investment.basic.trade.api.command.CancelMatchOrderCmd;
import fund.investment.basic.trade.api.command.PROrderCancelMatchRollbackCmd;
import fund.investment.basic.trade.api.enumeration.OrderMatchStatus;
import fund.investment.basic.trade.api.event.OrderCancelMatchSuccessEvt;
import fund.investment.basic.trade.api.event.PRCancelMatchRollbackEvt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Data
@Slf4j
public class UnmatchingOrderStateImpl extends OrderAggrState implements OrderMatchState {

    private OrderMatchStatus orderMatchStatus;

    public UnmatchingOrderStateImpl(OrderState orderState) {
        super(orderState);
    }

    @Override
    public void cancelMatch(CancelMatchOrderCmd cmd) {
        OrderCancelMatchSuccessEvt evt = new OrderCancelMatchSuccessEvt();
        evt.setId(cmd.getId());
        evt.setSkId(cmd.getSkId());
        evt.setRequestId(cmd.getRequestId());
        evt.setChLastModifiedId(cmd.getChLastModifiedId());
        evt.setInstrSkId(cmd.getInstructionId());
        evt.setTradeType(cmd.getTradeType());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托撤销匹配成功事件:{}", evt);
    }

    @Override
    public void autoCancel(AutoCancelMatchOrderCmd cmd) {
        OrderCancelMatchSuccessEvt evt = new OrderCancelMatchSuccessEvt();
        evt.setId(cmd.getId());
        evt.setSkId(cmd.getSkId());
        evt.setRequestId(cmd.getRequestId());
        evt.setChLastModifiedId(cmd.getChLastModifiedId());
//        evt.setInstrSkId(cmd.getInstrSkId());
        evt.setTradeType(cmd.getTradeType());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托撤销匹配成功事件:{}", evt);
    }

    @Override
    public void unmatchRollback(PROrderCancelMatchRollbackCmd cmd) {
        PRCancelMatchRollbackEvt evt = new PRCancelMatchRollbackEvt();
        evt.setId(cmd.getId());
        evt.setRequestId(cmd.getRequestId());
        evt.setErrorMsg(cmd.getErrorMsg());
        AggregateLifecycle.apply(evt);
    }
}
