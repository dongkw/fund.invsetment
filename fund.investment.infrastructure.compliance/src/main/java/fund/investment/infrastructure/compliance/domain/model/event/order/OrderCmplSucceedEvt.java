package fund.investment.infrastructure.compliance.domain.model.event.order;

import fund.investment.infrastructure.compliance.domain.model.event.ComplianceEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/10/10、2:22 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class OrderCmplSucceedEvt extends ComplianceEvent {

    private String orderId;

    public OrderCmplSucceedEvt(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
