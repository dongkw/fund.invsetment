package fund.investment.basic.trade.server.aggregate.status2;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CreateOrderCmd;
import fund.investment.basic.trade.api.command.CreateOrderConfirmCmd;
import fund.investment.basic.trade.api.command.CreateOrderFailedCmd;
import fund.investment.basic.trade.api.event.OrderCreateConfirmedEvt;
import fund.investment.basic.trade.api.event.OrderCreateCounterpartyEvt;
import fund.investment.basic.trade.api.event.OrderCreatedEvt;
import fund.investment.basic.trade.api.event.OrderFailedEvt;
import fund.investment.basic.trade.api.valueobject.SourceType;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.status.CreateConfirmState;
import fund.investment.basic.trade.server.aggregate.status.CreateState;
import fund.investment.basic.trade.server.aggregate.status.UnMatchState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/10、4:43 下午
 **/
public class StartState<T extends TradeElement> {
    public void handler(OrderAggregate<T> aggregate, CreateOrderCmd<T> cmd) {
        if (cmd.getSourceType() == SourceType.THIS_SIDE) {
            OrderCreatedEvt<T> evt = new OrderCreatedEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            AggregateLifecycle.apply(evt);
        } else {
            OrderCreateCounterpartyEvt<T> evt = new OrderCreateCounterpartyEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            AggregateLifecycle.apply(evt);
        }
    }

    public void handler(OrderAggregate<T> aggregate, CreateOrderConfirmCmd<T> cmd) {
        OrderCreateConfirmedEvt evt = new OrderCreateConfirmedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    public void handler(OrderAggregate<T> aggregate, CreateOrderFailedCmd cmd) {
        OrderFailedEvt evt = new OrderFailedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }


    public void on(OrderAggregate<T> aggregate, OrderCreatedEvt<T> evt) {
        BeanUtils.copyProperties(evt, aggregate);
        aggregate.setOrderState(new CreateState<>());
    }

    public void on(OrderAggregate<T> aggregate, OrderCreateConfirmedEvt evt) {
        aggregate.setOrderState(new CreateConfirmState<>());
    }

    public void on(OrderAggregate<T> aggregate, OrderCreateCounterpartyEvt<T> evt) {
        BeanUtils.copyProperties(evt, aggregate);
        aggregate.setOrderState(new UnMatchState<>());
    }

}
