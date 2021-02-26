package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.AutoCancelMatchOrderCmd;
import fund.investment.basic.trade.api.command.CancelMatchOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmingFillCmd;
import fund.investment.basic.trade.api.command.PRCancelMatchOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderMatchStatus;
import fund.investment.basic.trade.api.event.OrderAutoCancelMatchedEvt;
import fund.investment.basic.trade.api.event.OrderCancelMatchedEvt;
import fund.investment.basic.trade.api.event.PROrderUnmatchingEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class AutoMatchedOrderStateImpl extends OrderAggrState {

    private OrderMatchStatus orderMatchStatus;

    public AutoMatchedOrderStateImpl(OrderState orderState) {
        super(orderState);
        this.orderMatchStatus = OrderMatchStatus.AUTO;
    }

    public OrderMatchStatus getOrderMatchStatus() {
        return orderMatchStatus;
    }

    public void setOrderMatchStatus(OrderMatchStatus orderMatchStatus) {
        this.orderMatchStatus = orderMatchStatus;
    }

    @Override
    public void unmatching(PRCancelMatchOrderCmd cmd) {
        PROrderUnmatchingEvt evt = new PROrderUnmatchingEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void cancelMatch(CancelMatchOrderCmd cmd) {
        OrderCancelMatchedEvt evt = new OrderCancelMatchedEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托撤销匹配事件:{}", evt);
    }


    @Override
    public void fillConfirming(ConfirmingFillCmd cmd) {
        getOrderState().fillConfirming(cmd);
    }

    @Override
    public void autoCancel(AutoCancelMatchOrderCmd cmd) {
        OrderAutoCancelMatchedEvt evt = new OrderAutoCancelMatchedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送委托自动撤销匹配事件:{}", evt);
    }

}
