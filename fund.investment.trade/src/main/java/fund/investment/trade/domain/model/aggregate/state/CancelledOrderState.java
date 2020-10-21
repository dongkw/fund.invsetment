package fund.investment.trade.domain.model.aggregate.state;

public class CancelledOrderState extends OrderState{
	
	public CancelledOrderState() {
		super(OrderStatus.CANCELLED);
		
	}

}
