package fund.investment.instruction.exchange.stock.domain.model.valueobject;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.valueobject.OrderTradeElement;
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
