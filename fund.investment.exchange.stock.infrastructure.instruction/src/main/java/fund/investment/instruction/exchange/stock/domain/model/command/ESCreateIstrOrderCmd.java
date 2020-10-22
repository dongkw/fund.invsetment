package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.vo.OrderTradeElement;
import fund.investment.instruction.exchange.stock.domain.model.vo.ExchangeStockIstrOrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESCreateIstrOrderCmd extends CreateIstrOrderCmd {

    private ExchangeStockIstrOrderTradeElement exchangeStockIstrOrderTradeElement;

    public ESCreateIstrOrderCmd(String id, TradeType tradeType, String orderId, OrderTradeElement orderTradeElement, ExchangeStockIstrOrderTradeElement exchangeStockIstrOrderTradeElement) {
        super(id, tradeType, orderId, orderTradeElement);
        this.exchangeStockIstrOrderTradeElement = exchangeStockIstrOrderTradeElement;
    }
}
