package infrastructure.trade.exchange.stock.domain.model.event;

import infrastructure.trade.domain.model.event.OrderCreatedEvt;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
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

    public ESOrderCreatedEvt(String id, String instructionId, String tradeType, String unitId, String accountId, String userId, ExchangeStockOrderTradeElement orderTradeElement,
                             BigDecimal averageFillPrice, long totalFillQuantity, BigDecimal totalFillAmount, long totalCancelledQuantity, BigDecimal clearAmount) {
        super(id, instructionId, tradeType, unitId, accountId, userId);

        this.orderTradeElement = orderTradeElement;
        this.averageFillPrice = averageFillPrice;
        this.totalCancelledQuantity = totalCancelledQuantity;
        this.totalFillAmount = totalFillAmount;
        this.clearAmount = clearAmount;
        this.totalFillQuantity = totalFillQuantity;
    }
}
