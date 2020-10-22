package fund.investment.trade.domain.model.aggregate.state;

import org.axonframework.modelling.command.AggregateLifecycle;

import infrastructure.trade.domain.model.command.CancelConfirmOrderCmd;
import infrastructure.trade.domain.model.command.FillOrderCmd;
import infrastructure.trade.domain.model.event.OrderCancelledEvt;
import infrastructure.trade.domain.model.event.OrderFilledEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancellingOrderState extends OrderState {
	
	public CancellingOrderState() {
		super(OrderStatus.CANCELLING);
	}
	
	@Override
	public void cancelConfirm(CancelConfirmOrderCmd cmd) {
		log.info("Recieved Command: {}", cmd);
		
		OrderCancelledEvt evt = new OrderCancelledEvt(
				cmd.getId(), 
				cmd.getInstructionId(), 
				cmd.getTradeType(), 
				cmd.getUnitId(), 
				cmd.getCancelQuantity());
		AggregateLifecycle.apply(evt);
		log.info("Dispached Event: {}", evt);
	};
	
	@Override
	public void fill(FillOrderCmd cmd) {
		log.info("Recieved Command: {}", cmd);
		
		OrderFilledEvt evt = new OrderFilledEvt(
				cmd.getId(), 
				cmd.getInstructionId(), 
				cmd.getTradeType(),
				cmd.getFill());
		AggregateLifecycle.apply(evt);
		log.info("Dispached Event: {}", evt);
	}

}
