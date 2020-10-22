package infrastructure.trade.exchange.stock.domain.model.command;

import infrastructure.trade.domain.model.command.FillOrderCmd;
import infrastructure.trade.domain.model.valueobject.Fill;
import infrastructure.trade.domain.model.valueobject.OrderTradeElement;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESFillOrderCmd extends FillOrderCmd {

    private ExchangeStockOrderTradeElement exchangeStockOrderTradeElement;

    public ESFillOrderCmd(String id, String instructionId, String tradeType, Fill fill, ExchangeStockOrderTradeElement exchangeStockOrderTradeElement) {
        super(id, instructionId, tradeType, fill);
        this.exchangeStockOrderTradeElement = exchangeStockOrderTradeElement;
    }

    @Override
    public OrderTradeElement getOrderTradeElement() {
        return exchangeStockOrderTradeElement;
    }
}
