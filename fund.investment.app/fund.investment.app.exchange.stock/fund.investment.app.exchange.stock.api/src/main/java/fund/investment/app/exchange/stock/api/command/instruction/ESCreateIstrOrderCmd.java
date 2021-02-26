package fund.investment.app.exchange.stock.api.command.instruction;

import fund.investment.app.exchange.stock.api.valueobject.ExchangeStockIstrOrderTradeElement;
import fund.investment.basic.instruction.api.command.CreateIstrOrderCmd;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.valueobject.OrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESCreateIstrOrderCmd extends CreateIstrOrderCmd {

    private ExchangeStockIstrOrderTradeElement exchangeStockIstrOrderTradeElement;


    @Override
    public OrderTradeElement getOrderTradeElement() {
        return exchangeStockIstrOrderTradeElement;
    }
}

