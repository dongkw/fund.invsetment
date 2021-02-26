package fund.investment.app.exchange.stock.api.event.trade;

import fund.investment.app.exchange.stock.api.valueobject.ExchangeStockOrderTradeElement;
import fund.investment.basic.trade.api.event.OrderCreatedEvt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ESOrderCreatedEvt extends OrderCreatedEvt {

    private ExchangeStockOrderTradeElement orderTradeElement;

    private BigDecimal averageFillPrice;

    private long totalFillQuantity;

    private BigDecimal totalFillAmount;

    private long totalCancelledQuantity;

    private BigDecimal clearAmount;


}
