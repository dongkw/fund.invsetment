package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.FillOrderCmd;
import fund.investment.basic.trade.api.command.RejectOrderCmd;
import fund.investment.basic.trade.api.event.OrderFillEvt;
import fund.investment.basic.trade.api.event.OrderRejectEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class ReceivePlacedState<T extends TradeElement> extends PlacedState<T> {

    @Override
    public void handler(OrderAggregate<T> aggregate, RejectOrderCmd cmd) {
        OrderRejectEvt evt = new OrderRejectEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void handler(OrderAggregate<T> aggregate, FillOrderCmd<T> cmd) {
        OrderFillEvt evt = new OrderFillEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }


}
