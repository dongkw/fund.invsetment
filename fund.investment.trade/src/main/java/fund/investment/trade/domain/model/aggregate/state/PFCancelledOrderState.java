package fund.investment.trade.domain.model.aggregate.state;

import infrastructure.trade.domain.model.enumeration.OrderStatus;

public class PFCancelledOrderState extends OrderState {

    public PFCancelledOrderState() {
        super(OrderStatus.PARTIAL_FILLED_CANCELLED);
    }
}
