package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.MatchOrderConfirmCmd;
import fund.investment.basic.trade.api.event.OrderMatchConfirmEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class MatchState<T extends TradeElement> extends OrderState<T> {

    @Override
    public void handler(OrderAggregate<T> aggregate, MatchOrderConfirmCmd cmd) {
        OrderMatchConfirmEvt evt = new OrderMatchConfirmEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }


}
