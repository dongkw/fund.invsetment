package infrastructure.trade.exchange.stock.domain.model.valueobject;

import java.math.BigDecimal;

import infrastructure.trade.domain.model.valueobject.OrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExchangeStockOrderTradeElement extends OrderTradeElement {
	
	private String side;
	private BigDecimal price;
	private Long quantity;
	private BigDecimal amount;
	
	/**
	 * @param tradeType
	 * @param securityCode
	 * @param side
	 * @param price
	 * @param quantity
	 * @param amount
	 */
	public ExchangeStockOrderTradeElement(String tradeType, String securityCode, String side, BigDecimal price,
			Long quantity, BigDecimal amount) {
		super(tradeType, securityCode);
		this.side = side;
		this.price = price;
		this.quantity = quantity;
		this.amount = amount;
	}
	
	
	
}
