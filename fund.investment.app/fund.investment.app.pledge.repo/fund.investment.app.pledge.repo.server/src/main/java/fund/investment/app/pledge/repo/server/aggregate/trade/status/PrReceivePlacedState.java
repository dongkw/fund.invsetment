package fund.investment.app.pledge.repo.server.aggregate.trade.status;

import fund.investment.app.pledge.repo.api.event.trade.PROrderCancelEvt;
import fund.investment.app.pledge.repo.api.event.trade.PROrderFillEvt;
import fund.investment.app.pledge.repo.api.event.trade.PROrderRejectEvt;
import fund.investment.app.pledge.repo.api.event.trade.PROrderUpdateEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CancelOrderCmd;
import fund.investment.basic.trade.api.command.FillOrderCmd;
import fund.investment.basic.trade.api.command.RejectOrderCmd;
import fund.investment.basic.trade.api.command.UpdateOrderCmd;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.status.ReceivePlacedState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/8、10:18 上午
 **/
public class PrReceivePlacedState<T extends PledgeTradeElement> extends ReceivePlacedState<T> {

    @Override
    public void handler(OrderAggregate<T> aggregate, CancelOrderCmd cmd) {
        PROrderCancelEvt evt = new PROrderCancelEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void handler(OrderAggregate<T> aggregate, UpdateOrderCmd<T> cmd) {
        PROrderUpdateEvt evt = new PROrderUpdateEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    public void handler(OrderAggregate<T> aggregate, RejectOrderCmd cmd) {
        PROrderRejectEvt evt = new PROrderRejectEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void handler(OrderAggregate<T> aggregate, FillOrderCmd<T> cmd) {
        PROrderFillEvt evt = new PROrderFillEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

}
