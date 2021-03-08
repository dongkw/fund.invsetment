package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CancelOrderFailCmd;
import fund.investment.basic.trade.api.command.CancellingOrderCmd;
import fund.investment.basic.trade.api.event.OrderCancellingEvt;
import fund.investment.basic.trade.api.event.OrderFailedEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:18 上午
 **/
public class CancelState<T extends TradeElement> extends OrderState<T> {

    public void handler(OrderAggregate<T> aggregate, CancellingOrderCmd cmd) {
        OrderCancellingEvt evt = new OrderCancellingEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);

    }

    public void handler(OrderAggregate<T> aggregate, CancelOrderFailCmd cmd) {
        OrderFailedEvt evt = new OrderFailedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

}
