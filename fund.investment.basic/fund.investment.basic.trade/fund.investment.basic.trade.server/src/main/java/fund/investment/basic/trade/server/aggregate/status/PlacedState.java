package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.event.*;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class PlacedState<T extends TradeElement> extends OrderState<T> {


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
    @Override
    public void handler(OrderAggregate<T> aggregate, CounterpartyUpdateCmd<T> cmd) {
        OrderCounterpartyUpdateEvt<T> evt = new OrderCounterpartyUpdateEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
