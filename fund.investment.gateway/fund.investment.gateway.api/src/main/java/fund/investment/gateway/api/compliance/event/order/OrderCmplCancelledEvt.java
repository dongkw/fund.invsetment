package fund.investment.gateway.api.compliance.event.order;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
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
public class OrderCmplCancelledEvt extends ComplianceEvent {

    private String orderId;

    public OrderCmplCancelledEvt(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
