package fund.investment.trade.domain.model.aggregate.state;

import org.axonframework.modelling.command.AggregateLifecycle;

import infrastructure.trade.domain.model.command.CancelConfirmOrderCmd;
import infrastructure.trade.domain.model.command.FillOrderCmd;
import infrastructure.trade.domain.model.event.OrderFilledEvt;
import infrastructure.trade.domain.model.event.OrderPartialFilledCancelledEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PFCancellingOrderState extends OrderState{
	
	public PFCancellingOrderState() {
		super(OrderStatus.PARTIAL_FAILED_CANCELLING);
		
	}
	
	public void cancelConfirm(CancelConfirmOrderCmd cmd) {
		log.info("Recieved Command: {}", cmd);
		OrderPartialFilledCancelledEvt evt = new OrderPartialFilledCancelledEvt(
				cmd.getId(), 
				cmd.getInstructionId(), 
				cmd.getTradeType(), 
				cmd.getUnitId(),
				cmd.getCancelQuantity());
		AggregateLifecycle.apply(evt);
		log.info("Dispached Event: {}", evt);
		
	};
	
	/**
	 * Received Fill Report from Adaptor, trying to transfor status of OrderAggregation depends on filled or cancelled quantity
	 */
	@Override
	public void fill(FillOrderCmd cmd) { 
		log.info("Recieved Command: {}", cmd);
//		Object a = cmd.getFill();
//		if(a instanceof ExchangeStockFill) {
//			ExchangeStockOrderTradeElement esTradeElement = (ExchangeStockOrderTradeElement) cmd.getOrderTradeElement();
//			long filledQuantity = fills.stream().map(f -> (ExchangeStockFill)f).mapToLong(ExchangeStockFill::getFillQuantity).sum();
//			long esOrderQuantity = ESOrderQuantity(esTradeElement);
//			
//			//change state
//			OrderStatus status = (filledQuantity >= esOrderQuantity) ? OrderStatus.FAILED : OrderStatus.PARTIAL_FAILED;
//			setOrderStatus(status);
//			
//		}
		
		OrderFilledEvt evt = new OrderFilledEvt(
				cmd.getId(), 
				cmd.getInstructionId(), 
				cmd.getTradeType(), 
				cmd.getFill());
		AggregateLifecycle.apply(evt);
		log.info("Dispached Event: {}", evt);
		
	}

}
