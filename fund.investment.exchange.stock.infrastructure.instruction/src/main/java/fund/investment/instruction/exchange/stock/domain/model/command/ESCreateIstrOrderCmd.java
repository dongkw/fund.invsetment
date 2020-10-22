package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.valueobject.OrderTradeElement;
import fund.investment.instruction.exchange.stock.domain.model.valueobject.ExchangeStockIstrOrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESCreateIstrOrderCmd extends CreateIstrOrderCmd {

    private ExchangeStockIstrOrderTradeElement exchangeStockIstrOrderTradeElement;

    public ESCreateIstrOrderCmd(String id, TradeType tradeType, String orderId, ExchangeStockIstrOrderTradeElement exchangeStockIstrOrderTradeElement) {
        super(id, tradeType, orderId);
        this.exchangeStockIstrOrderTradeElement = exchangeStockIstrOrderTradeElement;
    }

    @Override
    public OrderTradeElement getOrderTradeElement() {
        return exchangeStockIstrOrderTradeElement;
    }
}

