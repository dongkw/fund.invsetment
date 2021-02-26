package fund.investment.app.exchange.stock.api.command.trade;

import fund.investment.app.exchange.stock.api.valueobject.ExchangeStockOrderTradeElement;
import fund.investment.basic.trade.api.command.FillOrderCmd;
import fund.investment.basic.trade.api.valueobject.OrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESFillOrderCmd extends FillOrderCmd {

    private ExchangeStockOrderTradeElement exchangeStockOrderTradeElement;

}
