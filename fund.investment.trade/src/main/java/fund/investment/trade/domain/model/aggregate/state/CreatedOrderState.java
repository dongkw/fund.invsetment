package fund.investment.trade.domain.model.aggregate.state;

import org.axonframework.modelling.command.AggregateLifecycle;

import infrastructure.trade.domain.model.command.ConfirmOrderCmd;
import infrastructure.trade.domain.model.event.OrderConfirmedEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreatedOrderState extends CanFailOrderState {
	
	public CreatedOrderState() {
		super(OrderStatus.CREATED);
	}
	
	@Override
	public void createConfirm(ConfirmOrderCmd cmd) {
		log.info("Recieved Command: {}", cmd);
		OrderConfirmedEvt evt = new OrderConfirmedEvt(
				cmd.getId(), 
				cmd.getInstructionId(), 
				cmd.getTradeType());
		AggregateLifecycle.apply(evt);
		log.info("Dispached Event: {}", evt);

	};

}
