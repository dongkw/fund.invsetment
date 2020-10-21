package fund.investment.trade.domain.model.aggregate.state;

import org.axonframework.modelling.command.AggregateLifecycle;

import infrastructure.trade.domain.model.command.PlaceOrderCmd;
import infrastructure.trade.domain.model.event.OrderPlacingEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfirmedOrderState extends CanFailOrderState{
	
	public ConfirmedOrderState() {
		super(OrderStatus.CONFIRMED);
	}
	
	@Override
	public void placing(PlaceOrderCmd cmd) {
		log.info("Recieved Command: {}", cmd);
		OrderPlacingEvt evt = new OrderPlacingEvt(
				cmd.getId(), 
				cmd.getInstructionId(), 
				cmd.getTradeType(), 
				cmd.getExchangeId());
		AggregateLifecycle.apply(evt);
		log.info("Dispached Event: {}", evt);

	}

}
