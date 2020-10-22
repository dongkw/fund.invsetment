package fund.investment.trade.domain.model.aggregate.state;

import infrastructure.trade.domain.model.enumeration.OrderStatus;

public class CompletedOrderState extends OrderState {

    public CompletedOrderState() {
        super(OrderStatus.COMPLETED);
    }
}
