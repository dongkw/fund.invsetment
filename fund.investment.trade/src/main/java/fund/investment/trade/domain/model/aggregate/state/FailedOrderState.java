package fund.investment.trade.domain.model.aggregate.state;

public class FailedOrderState extends OrderState{
	
	public FailedOrderState() {
		super(OrderStatus.FAILED);
		
	}

}
