package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.FillOrderConfirmCmd;
import fund.investment.basic.trade.api.event.OrderFillConfirmEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:16 上午
 **/
public class FillState<T extends TradeElement> extends OrderState<T> {
    public void handler(OrderAggregate<T> aggregate, FillOrderConfirmCmd cmd) {
        OrderFillConfirmEvt evt = new OrderFillConfirmEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
