package fund.investment.trade.exchange.stock.domain.model.aggregate;

import java.math.BigDecimal;

import fund.investment.trade.domain.model.aggregate.OrderAggregate;
import fund.investment.trade.domain.model.aggregate.state.CompletedOrderState;
import fund.investment.trade.domain.model.aggregate.state.CreatedOrderState;
import fund.investment.trade.domain.model.aggregate.state.PartialFilledOrderState;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


import fund.investment.trade.exchange.stock.domain.model.entity.ExchangeStockFill;
import infrastructure.trade.exchange.stock.domain.model.command.ESCancelOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.command.ESCreateOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.command.ESFailOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.command.ESFillOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderCreatedEvt;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderFilledEvt;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
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
@Aggregate
public class ExchangeStockOrderAggregate extends OrderAggregate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5281304340312921648L;
	
	private BigDecimal averageFillPrice = BigDecimal.ZERO;
	private Long totalFillQuantity = 0l;
	private BigDecimal totalFillAmount = BigDecimal.ZERO;
	private Long totalCancelledQuantity = 0l;
	private BigDecimal clearAmount = BigDecimal.ZERO;
	
	@CommandHandler
	public ExchangeStockOrderAggregate(ESCreateOrderCmd cmd) {
		log.info("Recieved Command: {}", cmd);

		ESOrderCreatedEvt evt = new ESOrderCreatedEvt(
				cmd.getId(), 
				cmd.getInstructionId(), 
				cmd.getTradeType(), 
				cmd.getUnitId(), 
				cmd.getAccountId(), 
				cmd.getUserId(), 
				cmd.getOrderTradeElement(),
				cmd.getAverageFillPrice(),
				cmd.getTotalFillQuantity(),
				cmd.getTotalFillAmount(),
				cmd.getTotalCancelledQuantity(),
				cmd.getClearAmount());
		AggregateLifecycle.apply(evt); 
		
		log.info("Dispach Event: {}", evt);
		
	}
	
	@CommandHandler
	public void on(ESFailOrderCmd cmd) {
		getOrderState().fail(cmd);
		
	}

	@CommandHandler
	public void on(ESCancelOrderCmd cmd) {
		getOrderState().cancel(cmd);
		
	}
	
	@CommandHandler
	public void on(ESFillOrderCmd cmd) {
		getOrderState().fill(cmd);
		
	}
	
	@EventSourcingHandler
	public void on(ESOrderCreatedEvt evt) {
		log.info("Recieved Event: {}", evt);
		
		setId(evt.getId());
		setOrderState(new CreatedOrderState());
		setInstructionId(evt.getInstructionId());
		setTradeType(evt.getTradeType());
		setUnitId(evt.getUnitId());
		setAccountId(evt.getAccountId());
		setUserId(evt.getUserId());
		setTradeElement(evt.getOrderTradeElement());
		
		this.averageFillPrice = evt.getAverageFillPrice();
		this.totalCancelledQuantity += evt.getTotalCancelledQuantity();
		this.totalFillAmount = evt.getTotalFillAmount();
		this.clearAmount = evt.getClearAmount();
		this.totalFillQuantity = evt.getTotalFillQuantity();
		
	}

	@EventSourcingHandler
	public void on(ESOrderFilledEvt evt) {
		log.info("Recieved Event: {}", evt);
		
		setInstructionId(evt.getInstructionId());
		setTradeType(evt.getTradeType());
		getFills().add(evt.getFill());
		
		completedIfSatisfy(evt);
		partialFilledIfSatisfy(evt);
	}
	
	private void completedIfSatisfy(ESOrderFilledEvt evt) {
		if(!checkSatisfy(evt)) 
			return;
		
		setOrderState(new CompletedOrderState());
		
	}
	
	private void partialFilledIfSatisfy(ESOrderFilledEvt evt) {
		if(checkSatisfy(evt)) 
			return;
		
		setOrderState(new PartialFilledOrderState());
		
	}
	
	private boolean checkSatisfy(ESOrderFilledEvt evt) {
		Long filledQuantity = getFills().stream().mapToLong(it -> {
			ExchangeStockFill fillItem = (ExchangeStockFill)it;
			return fillItem.getFillQuantity();
			
		}).sum();
		ExchangeStockOrderTradeElement tradeElement = (ExchangeStockOrderTradeElement) getTradeElement();
		return filledQuantity >= tradeElement.getQuantity();
		
	}
	
}
