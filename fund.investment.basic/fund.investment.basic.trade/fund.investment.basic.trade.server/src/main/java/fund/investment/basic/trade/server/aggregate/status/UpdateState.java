package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.UpdateOrderConfirmCmd;
import fund.investment.basic.trade.api.command.UpdateOrderFailCmd;
import fund.investment.basic.trade.api.event.OrderFailedEvt;
import fund.investment.basic.trade.api.event.OrderUpdateConfirmEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:18 上午
 **/
public class UpdateState<T extends TradeElement> extends OrderState<T> {

    @Override
    public void handler(OrderAggregate<T> aggregate, UpdateOrderConfirmCmd<T> cmd) {
        OrderUpdateConfirmEvt<T> evt = new OrderUpdateConfirmEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void handler(OrderAggregate<T> aggregate, UpdateOrderFailCmd cmd) {
        OrderFailedEvt evt = new OrderFailedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

}
