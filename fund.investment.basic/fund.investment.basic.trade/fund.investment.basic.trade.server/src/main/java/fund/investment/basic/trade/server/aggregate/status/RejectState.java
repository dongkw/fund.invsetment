package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.RejectOrderConfirmCmd;
import fund.investment.basic.trade.api.event.OrderRejectConfirmedEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:16 上午
 **/
public class RejectState<T extends TradeElement> extends OrderState<T> {
    @Override
    public void handler(OrderAggregate<T> aggregate, RejectOrderConfirmCmd cmd) {
        OrderRejectConfirmedEvt evt = new OrderRejectConfirmedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }


}
