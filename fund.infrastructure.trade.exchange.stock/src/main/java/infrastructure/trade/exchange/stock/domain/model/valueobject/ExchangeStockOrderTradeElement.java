package infrastructure.trade.exchange.stock.domain.model.valueobject;

import java.math.BigDecimal;

import infrastructure.trade.domain.model.valueobject.OrderTradeElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExchangeStockOrderTradeElement extends OrderTradeElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5076170226178351115L;
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
