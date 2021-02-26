package fund.investment.basic.trade.server.aggregate.state;


import fund.investment.basic.trade.api.enumeration.OrderStatus;

public class CompletedOrderState extends OrderState {

    public CompletedOrderState() {
        super(OrderStatus.COMPLETED);
    }
}
