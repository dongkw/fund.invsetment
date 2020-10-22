package fund.investment.trade.domain.model.aggregate.state;

import infrastructure.trade.domain.model.enumeration.OrderStatus;

public class FailedOrderState extends OrderState {

    public FailedOrderState() {
        super(OrderStatus.FAILED);
    }
}
