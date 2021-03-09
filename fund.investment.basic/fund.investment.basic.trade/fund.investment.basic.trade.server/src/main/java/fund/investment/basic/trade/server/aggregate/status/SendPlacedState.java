package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CounterpartyFillOrdeCmd;
import fund.investment.basic.trade.api.command.CounterpartyRejectOrderCmd;
import fund.investment.basic.trade.api.event.OrderFillConfirmEvt;
import fund.investment.basic.trade.api.event.OrderRejectConfirmedEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class SendPlacedState<T extends TradeElement> extends PlacedState<T> {
    @Override
    public void handler(OrderAggregate<T> aggregate, CounterpartyRejectOrderCmd cmd) {
        OrderRejectConfirmedEvt evt = new OrderRejectConfirmedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
    @Override
    public void handler(OrderAggregate<T> aggregate, CounterpartyFillOrdeCmd cmd) {
        OrderFillConfirmEvt evt = new OrderFillConfirmEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
