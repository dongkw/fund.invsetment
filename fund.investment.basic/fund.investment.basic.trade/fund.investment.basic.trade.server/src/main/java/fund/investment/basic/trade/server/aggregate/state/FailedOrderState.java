package fund.investment.basic.trade.server.aggregate.state;


import fund.investment.basic.trade.api.enumeration.OrderStatus;

public class FailedOrderState extends OrderState {

    public FailedOrderState() {
        super(OrderStatus.FAILED);
    }
}
