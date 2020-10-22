package fund.investment.trade.domain.model.aggregate.state;

import infrastructure.trade.domain.model.enumeration.OrderStatus;

public class CancelledOrderState extends OrderState {

    public CancelledOrderState() {
        super(OrderStatus.CANCELLED);
    }
}
