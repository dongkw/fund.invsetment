package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.CancelOrderCmd;
import fund.investment.basic.trade.api.command.PlaceCancelOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfirmedOrderState extends CanFailOrderState {

    public ConfirmedOrderState(OrderStatus orderStatus) {
        super(orderStatus);
    }

    @Override
    public void cancel(CancelOrderCmd cmd) {
        log.debug("未报的委托不需要发送撤销委托:{}", cmd);
    }

    @Override
    public void cancelling(PlaceCancelOrderCmd cmd) {
        log.debug("未报的委托不需要发送撤销委托:{}", cmd);
    }


    @Override
    public void delete(CancelOrderCmd cmd) {
        //未报前可以删除
        new CreatedOrderState().delete(cmd);
    }

}
