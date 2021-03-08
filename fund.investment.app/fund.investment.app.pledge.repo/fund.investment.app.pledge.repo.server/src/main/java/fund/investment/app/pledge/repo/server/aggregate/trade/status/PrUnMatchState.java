package fund.investment.app.pledge.repo.server.aggregate.trade.status;

import fund.investment.app.pledge.repo.api.event.trade.PROrderMatchEvt;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.MatchOrderCmd;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.OrderState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:09 上午
 **/
public class PrUnMatchState<T extends TradeElement> extends OrderState<T> {

    @Override
    public void handler(OrderAggregate<T> aggregate, MatchOrderCmd cmd) {
        PROrderMatchEvt evt = new PROrderMatchEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }


}
