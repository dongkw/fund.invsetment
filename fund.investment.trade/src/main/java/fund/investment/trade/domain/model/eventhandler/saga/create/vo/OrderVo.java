package fund.investment.trade.domain.model.eventhandler.saga.create.vo;

import java.util.Set;

import lombok.Data;

@Data
public class OrderVo {

    private String orderId;
    
    private String istrId;
    
    private String unitId;
    
    private String securityCode;
    
    private Set<OrderSagaStatus> statuses;
}
