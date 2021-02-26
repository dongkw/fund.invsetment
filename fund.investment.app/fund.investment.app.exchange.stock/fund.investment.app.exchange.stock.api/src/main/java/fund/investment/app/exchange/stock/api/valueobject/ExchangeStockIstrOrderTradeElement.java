package fund.investment.app.exchange.stock.api.valueobject;

import fund.investment.basic.instruction.api.enumeration.TradeSide;
import fund.investment.basic.instruction.api.valueobject.OrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExchangeStockIstrOrderTradeElement extends OrderTradeElement {

    private String price;

    private TradeSide side;

    private long amount;

    public ExchangeStockIstrOrderTradeElement(String tradeType, String securityCode, long quantity, String price, TradeSide side, long amount) {
        super(tradeType, securityCode, quantity);
        this.price = price;
        this.side = side;
        this.amount = amount;
    }
}
