package infrastructure.trade.exchange.stock.domain.model.event;

import java.math.BigDecimal;

import infrastructure.trade.domain.model.event.OrderCreatedEvt;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ESOrderCreatedEvt extends OrderCreatedEvt{
	private ExchangeStockOrderTradeElement orderTradeElement;
	private BigDecimal averageFillPrice;
	private Long totalFillQuantity;
	private BigDecimal totalFillAmount;
	private Long totalCancelledQuantity;
	private BigDecimal clearAmount;
	
	public ESOrderCreatedEvt(String id, String instructionId, String tradeType, String unitId, String accountId, String userId, ExchangeStockOrderTradeElement orderTradeElement, 
			BigDecimal averageFillPrice, Long totalFillQuantity, BigDecimal totalFillAmount, Long totalCancelledQuantity, BigDecimal clearAmount) {
		super(id, instructionId, tradeType, unitId, accountId, userId);
		
		this.orderTradeElement = orderTradeElement;
		this.averageFillPrice = averageFillPrice;
		this.totalCancelledQuantity = totalCancelledQuantity;
		this.totalFillAmount = totalFillAmount;
		this.clearAmount = clearAmount;
		this.totalFillQuantity = totalFillQuantity;
	}

}
