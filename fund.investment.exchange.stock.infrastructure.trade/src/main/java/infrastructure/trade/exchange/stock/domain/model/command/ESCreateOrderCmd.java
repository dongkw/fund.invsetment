package infrastructure.trade.exchange.stock.domain.model.command;

import infrastructure.trade.domain.model.command.CreateOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
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

    public ESCreateOrderCmd(String id, String instructionId, String tradeType, String unitId, String accountId, String userId, ExchangeStockOrderTradeElement orderTradeElement,
                            BigDecimal averageFillPrice, long totalFillQuantity, BigDecimal totalFillAmount, long totalCancelledQuantity, BigDecimal clearAmount) {

        super(id, instructionId, tradeType, unitId, accountId, userId);

        this.orderTradeElement = orderTradeElement;
        this.averageFillPrice = this.averageFillPrice.add(averageFillPrice);
        this.totalCancelledQuantity += totalCancelledQuantity;
        this.totalFillAmount = this.totalFillAmount.add(totalFillAmount);
        this.clearAmount = this.clearAmount.add(clearAmount);
        this.totalFillQuantity += totalFillQuantity;
    }
}
