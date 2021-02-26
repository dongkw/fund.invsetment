package fund.investment.app.exchange.stock.server.entity.trade;

import fund.investment.basic.trade.api.valueobject.Fill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeStockFill extends Fill {

    private String side;

    private BigDecimal fillPrice;

    private long fillQuantity;

    private BigDecimal fillAmount;

    private BigDecimal clearAmount;

    public ExchangeStockFill(String id, String orderOriginalId, String orderExchangeId, String instructionId,
                             String tradeType, String securityCode, LocalDateTime fillTime, String side, BigDecimal fillPrice,
                             long fillQuantity, BigDecimal fillAmount, BigDecimal clearAmount) {
        super(id, orderOriginalId, orderExchangeId, instructionId, tradeType, securityCode, fillTime);
        this.side = side;
        this.fillPrice = fillPrice;
        this.fillQuantity = fillQuantity;
        this.fillAmount = fillAmount;
        this.clearAmount = clearAmount;
    }
}
