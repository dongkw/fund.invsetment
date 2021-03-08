package fund.investment.basic.trade.server.aggregate.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.MatchOrderConfirmCmd;
import fund.investment.basic.trade.api.event.OrderMatchSuccessEvt;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class MatchState<T extends TradeElement> extends OrderState<T> {


    public void handler(OrderAggregate<T> aggregate, MatchOrderConfirmCmd cmd) {
        OrderMatchSuccessEvt evt = new OrderMatchSuccessEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }


}
