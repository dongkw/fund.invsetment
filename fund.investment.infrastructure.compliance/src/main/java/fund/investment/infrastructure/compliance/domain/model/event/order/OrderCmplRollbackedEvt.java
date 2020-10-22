package fund.investment.infrastructure.compliance.domain.model.event.order;

import fund.investment.infrastructure.compliance.domain.model.event.ComplianceEvent;
import lombok.*;

/**
 * @Author dongkw
 * @Date 2020/10/10、2:22 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class OrderCmplRollbackedEvt extends ComplianceEvent {
    private String orderId;

    public OrderCmplRollbackedEvt(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
