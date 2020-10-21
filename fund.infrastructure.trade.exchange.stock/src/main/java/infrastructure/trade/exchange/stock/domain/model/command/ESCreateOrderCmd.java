package infrastructure.trade.exchange.stock.domain.model.command;

import java.math.BigDecimal;

import fund.investment.infrastructure.util.LoggerTemplate;
import infrastructure.trade.domain.model.command.CreateOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ESCreateOrderCmd extends CreateOrderCmd{
	
	private ExchangeStockOrderTradeElement orderTradeElement;
	private BigDecimal averageFillPrice = BigDecimal.ZERO;
	private Long totalFillQuantity = 0l;
	private BigDecimal totalFillAmount = BigDecimal.ZERO;
	private Long totalCancelledQuantity = 0l;
	private BigDecimal clearAmount = BigDecimal.ZERO;
	
	public ESCreateOrderCmd(String id, String instructionId, String tradeType, String unitId, String accountId, String userId, ExchangeStockOrderTradeElement orderTradeElement, 
			BigDecimal averageFillPrice, Long totalFillQuantity, BigDecimal totalFillAmount, Long totalCancelledQuantity, BigDecimal clearAmount) {
		
		super(id, instructionId, tradeType, unitId, accountId, userId);
		
		this.orderTradeElement = orderTradeElement;
		this.averageFillPrice = this.averageFillPrice.add(averageFillPrice);
		this.totalCancelledQuantity += totalCancelledQuantity;
		this.totalFillAmount = this.totalFillAmount.add(totalFillAmount);
		this.clearAmount = this.clearAmount.add(clearAmount);
		this.totalFillQuantity += totalFillQuantity;
		
	}

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}

}
