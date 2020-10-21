package fund.investment.trade.domain.model.aggregate;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;

import fund.investment.trade.domain.model.aggregate.state.CancellingOrderState;
import fund.investment.trade.domain.model.aggregate.state.CompletedOrderState;
import fund.investment.trade.domain.model.aggregate.state.ConfirmedOrderState;
import fund.investment.trade.domain.model.aggregate.state.FailedOrderState;
import fund.investment.trade.domain.model.aggregate.state.OrderState;
import fund.investment.trade.domain.model.aggregate.state.PFCancellingOrderState;
import fund.investment.trade.domain.model.aggregate.state.PlacedOrderState;
import fund.investment.trade.domain.model.aggregate.state.PlacingOrderState;
import infrastructure.trade.domain.model.command.CancelConfirmOrderCmd;
import infrastructure.trade.domain.model.command.ConfirmOrderCmd;
import infrastructure.trade.domain.model.command.PlaceCancelOrderCmd;
import infrastructure.trade.domain.model.command.PlaceConfirmOrderCmd;
import infrastructure.trade.domain.model.command.PlaceOrderCmd;
import infrastructure.trade.domain.model.event.OrderCancellingEvt;
import infrastructure.trade.domain.model.event.OrderCompletedEvt;
import infrastructure.trade.domain.model.event.OrderConfirmedEvt;
import infrastructure.trade.domain.model.event.OrderFailedEvt;
import infrastructure.trade.domain.model.event.OrderPartialFilledCancellingEvt;
import infrastructure.trade.domain.model.event.OrderPlacedEvt;
import infrastructure.trade.domain.model.event.OrderPlacingEvt;
import infrastructure.trade.domain.model.valueobject.Fill;
import infrastructure.trade.domain.model.valueobject.OrderTradeElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderAggregate extends DomainAggregate{
	/**
	 * 
	 */
	private static final long serialVersionUID = -911131721681328442L;
	
	
	@AggregateIdentifier
	private String id;
	private OrderState orderState;
	private LocalDateTime orderTime;
	private String errorCode;
	private String errorMsg;
	private String exchangeId;
	private String instructionId;
	private String unitId;
	private String accountId;
	private String userId;
	private String tradeType;
	private OrderTradeElement tradeElement;
	private List<Fill> fills = new LinkedList<Fill>();
	
	@CommandHandler
	public void handle(ConfirmOrderCmd cmd) {
		orderState.createConfirm(cmd);	
		
	}

	@CommandHandler
	public void handle(PlaceOrderCmd cmd) {
		orderState.placing(cmd);
		
	}

	@CommandHandler
	public void handle(PlaceConfirmOrderCmd cmd) {
		orderState.placed(cmd);
		
	}

	@CommandHandler
	public void handle(PlaceCancelOrderCmd cmd) {
		orderState.cancelling(cmd);
		
	}

	@CommandHandler
	public void handle(CancelConfirmOrderCmd cmd) {
		orderState.cancelConfirm(cmd);
		
	}

	@EventSourcingHandler
	public void on(OrderFailedEvt evt) {
		log.info("Recieved Event: {}", evt);
		this.orderState = new FailedOrderState();
		this.instructionId = evt.getInstructionId();
		this.tradeType = evt.getTradeType();
		//todo: deal failMsg
	}

	@EventSourcingHandler
	public void on(OrderConfirmedEvt evt) {
		log.info("Recieved Event: {}", evt);
		this.orderState = new ConfirmedOrderState();
		this.instructionId = evt.getInstructionId();
		this.tradeType = evt.getTradeType();
		
	}

	@EventSourcingHandler
	public void on(OrderCancellingEvt evt) {
		log.info("Recieved Event: {}", evt);
		this.orderState = new CancellingOrderState();
		this.instructionId = evt.getInstructionId();
		this.tradeType = evt.getTradeType();
	}
	
	@EventSourcingHandler
	public void on(OrderPartialFilledCancellingEvt evt) {
		log.info("Recieved Event: {}", evt);
		this.orderState = new PFCancellingOrderState();
		this.instructionId = evt.getInstructionId();
		this.tradeType = evt.getTradeType();
	}

	@EventSourcingHandler
	public void on(OrderPlacingEvt evt) {
		log.info("Recieved Event: {}", evt);
		this.orderState = new PlacingOrderState();
		this.instructionId = evt.getInstructionId();
		this.tradeType = evt.getTradeType();
		this.exchangeId = evt.getExchangId();
	}

	@EventSourcingHandler
	public void on(OrderPlacedEvt evt) {
		log.info("Recieved Event: {}", evt);
		this.orderState = new PlacedOrderState();
		this.instructionId = evt.getInstructionId();
		this.tradeType = evt.getTradeType();
		//todo: deal with orderTime
	}

	@EventSourcingHandler
	public void on(OrderCompletedEvt evt) {
		log.info("Recieved Event: {}", evt);
		this.orderState = new CompletedOrderState();
		this.instructionId = evt.getInstructionId();
		this.tradeType = evt.getTradeType();
	}
	
		
}
