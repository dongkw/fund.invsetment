package fund.investment.basic.trade.server.aggregate.state;


import fund.investment.basic.trade.api.enumeration.OrderStatus;

public class CancelledOrderState extends OrderState {

    public CancelledOrderState() {
        super(OrderStatus.CANCELLED);
    }
}
