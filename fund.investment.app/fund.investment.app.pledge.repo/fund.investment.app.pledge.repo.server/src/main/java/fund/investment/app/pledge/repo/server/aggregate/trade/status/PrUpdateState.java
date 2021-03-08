package fund.investment.app.pledge.repo.server.aggregate.trade.status;

import fund.investment.app.pledge.repo.api.event.trade.PROrderUpdateConfirmEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.UpdateOrderConfirmCmd;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.status.UpdateState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/4、9:18 上午
 **/
public class PrUpdateState<T extends PledgeTradeElement> extends UpdateState<T> {

    @Override
    public void handler(OrderAggregate<T> aggregate, UpdateOrderConfirmCmd<T> cmd) {
        PROrderUpdateConfirmEvt<T> evt = new PROrderUpdateConfirmEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }


}
