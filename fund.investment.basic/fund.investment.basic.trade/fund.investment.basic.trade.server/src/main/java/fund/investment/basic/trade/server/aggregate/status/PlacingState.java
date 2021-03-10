package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CancelOrderCmd;
import fund.investment.basic.trade.api.command.PlacedOrderCmd;
import fund.investment.basic.trade.api.command.UpdateOrderCmd;
import fund.investment.basic.trade.api.event.OrderCancelEvt;
import fund.investment.basic.trade.api.event.OrderPlacedEvt;
import fund.investment.basic.trade.api.event.OrderUpdateEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class PlacingState<T extends TradeElement> extends OrderState<T> {
    @Override
    public void handler(OrderAggregate<T> aggregate, PlacedOrderCmd<T> cmd) {
        OrderPlacedEvt<T> evt = new OrderPlacedEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);

    }

    @Override
    public void handler(OrderAggregate<T> aggregate, CancelOrderCmd cmd) {
        OrderCancelEvt evt = new OrderCancelEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void handler(OrderAggregate<T> aggregate, UpdateOrderCmd<T> cmd) {
        OrderUpdateEvt<T> evt = new OrderUpdateEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
