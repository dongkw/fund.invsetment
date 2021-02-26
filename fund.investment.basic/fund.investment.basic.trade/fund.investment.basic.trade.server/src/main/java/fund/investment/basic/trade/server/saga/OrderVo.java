package fund.investment.basic.trade.server.saga;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Data;

@Data
public class OrderVo {

    private String orderId;

    private String istrId;

    private String unitId;

    private String securityCode;

    private TradeType tradeType;
    
    private long price;

    private long amount;

    private long quantity;
}
