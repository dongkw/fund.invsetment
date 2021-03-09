package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CancelledOrderCmd;
import fund.investment.basic.trade.api.event.OrderCancelledEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:18 上午
 **/
public class CancellingState<T extends TradeElement> extends OrderState<T> {
    @Override
    public void handler(OrderAggregate<T> aggregate, CancelledOrderCmd cmd) {
        OrderCancelledEvt evt = new OrderCancelledEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
