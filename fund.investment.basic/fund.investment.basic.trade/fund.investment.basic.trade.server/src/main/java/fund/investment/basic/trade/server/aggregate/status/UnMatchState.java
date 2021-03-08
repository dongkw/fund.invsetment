package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.AutoMatchOrderCmd;
import fund.investment.basic.trade.api.command.MatchOrderCmd;
import fund.investment.basic.trade.api.event.OrderMatchEvt;
import fund.investment.basic.trade.api.event.OrderMatchSuccessEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class UnMatchState<T extends TradeElement> extends OrderState<T> {
    public void handler(OrderAggregate<T> aggregate, MatchOrderCmd cmd) {
        OrderMatchEvt evt = new OrderMatchEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    public void handler(OrderAggregate<T> aggregate, AutoMatchOrderCmd cmd) {
        OrderMatchSuccessEvt evt = new OrderMatchSuccessEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

}
