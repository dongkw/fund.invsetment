package fund.investment.trade.domain.model.aggregate.state;

public class PFCancelledOrderState extends OrderState{
	
	public PFCancelledOrderState() {
		super(OrderStatus.PARTIAL_FAILED_CANCELLED);
		
	}

}
