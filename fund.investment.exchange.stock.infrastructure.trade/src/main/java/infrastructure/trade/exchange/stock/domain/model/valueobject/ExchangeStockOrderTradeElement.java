package infrastructure.trade.exchange.stock.domain.model.valueobject;

import infrastructure.trade.domain.model.valueobject.OrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ExchangeStockOrderTradeElement extends OrderTradeElement {

    private String side;

    private BigDecimal price;

    private long quantity;

    private BigDecimal amount;

    public ExchangeStockOrderTradeElement(String tradeType, String securityCode, String side, BigDecimal price,
                                          long quantity, BigDecimal amount) {
        super(tradeType, securityCode);
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }
}
