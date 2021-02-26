package fund.investment.basic.trade.server.aggregate.state;


import fund.investment.basic.trade.api.enumeration.OrderStatus;

public class DeletedOrderState extends OrderState {

    public DeletedOrderState() {
        super(OrderStatus.DELETED);
    }
}
