package fund.investment.trade.domain.model.eventhandler.saga.create.valueobject;

import lombok.Data;

import java.util.Set;

@Data
public class OrderValueObject {

    private String orderId;

    private String istrId;

    private String unitId;

    private String securityCode;

    private Set<OrderSagaStatus> statuses;
}
