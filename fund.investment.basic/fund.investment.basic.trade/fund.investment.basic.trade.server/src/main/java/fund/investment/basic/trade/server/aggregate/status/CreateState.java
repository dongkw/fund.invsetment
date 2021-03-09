package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CreateOrderConfirmCmd;
import fund.investment.basic.trade.api.command.CreateOrderFailedCmd;
import fund.investment.basic.trade.api.event.OrderCreateConfirmedEvt;
import fund.investment.basic.trade.api.event.OrderFailedEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class CreateState<T extends TradeElement> extends OrderState<T> {

    @Override
    public void handler(OrderAggregate<T> aggregate, CreateOrderConfirmCmd<T> cmd) {
        OrderCreateConfirmedEvt evt = new OrderCreateConfirmedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
    @Override
    public void handler(OrderAggregate<T> aggregate, CreateOrderFailedCmd cmd) {
        OrderFailedEvt evt = new OrderFailedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
