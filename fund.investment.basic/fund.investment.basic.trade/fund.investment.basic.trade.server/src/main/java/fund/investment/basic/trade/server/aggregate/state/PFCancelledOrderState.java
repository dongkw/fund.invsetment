package fund.investment.basic.trade.server.aggregate.state;


import fund.investment.basic.trade.api.enumeration.OrderStatus;

public class PFCancelledOrderState extends OrderState {

    public PFCancelledOrderState() {
        super(OrderStatus.PARTIAL_FILLED_CANCELLED);
    }
}
