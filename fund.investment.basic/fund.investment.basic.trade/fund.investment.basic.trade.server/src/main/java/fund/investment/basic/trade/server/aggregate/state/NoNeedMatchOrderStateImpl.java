package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.ConfirmingFillCmd;
import fund.investment.basic.trade.api.enumeration.OrderMatchStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoNeedMatchOrderStateImpl extends OrderAggrState {

    private OrderMatchStatus orderMatchStatus;

    /**
     * 无需匹配
     */
    public NoNeedMatchOrderStateImpl(OrderState orderState) {
        super(orderState);
        this.orderMatchStatus = OrderMatchStatus.NONEED;
    }

    public OrderMatchStatus getOrderMatchStatus() {
        return orderMatchStatus;
    }

    public void setOrderMatchStatus(OrderMatchStatus orderMatchStatus) {
        this.orderMatchStatus = orderMatchStatus;
    }

    @Override
    public void fillConfirming(ConfirmingFillCmd cmd) {
        getOrderState().fillConfirming(cmd);
    }

}
