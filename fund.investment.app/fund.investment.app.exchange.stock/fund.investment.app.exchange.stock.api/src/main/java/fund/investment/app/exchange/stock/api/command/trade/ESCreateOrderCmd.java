package fund.investment.app.exchange.stock.api.command.trade;

import fund.investment.app.exchange.stock.api.valueobject.ExchangeStockOrderTradeElement;
import fund.investment.basic.trade.api.command.CreateOrderCmd;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ESCreateOrderCmd extends CreateOrderCmd {

    private ExchangeStockOrderTradeElement orderTradeElement;

    private BigDecimal averageFillPrice = BigDecimal.ZERO;

    private long totalFillQuantity;

    private BigDecimal totalFillAmount = BigDecimal.ZERO;

    private long totalCancelledQuantity;

    private BigDecimal clearAmount = BigDecimal.ZERO;

}
