package fund.investment.trade.domain.model.aggregate.state;

public class CompletedOrderState extends OrderState{
	
	public CompletedOrderState() {
		super(OrderStatus.COMPLETED);
		
	}

}
