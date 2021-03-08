package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.MatchOrderCancelCmd;
import fund.investment.basic.trade.api.event.OrderCancelMatchEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class MatchConfirmState<T extends TradeElement> extends ReceivePlacedState<T> {


    public void handler(OrderAggregate<T> aggregate, MatchOrderCancelCmd cmd) {
        OrderCancelMatchEvt evt = new OrderCancelMatchEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

}
