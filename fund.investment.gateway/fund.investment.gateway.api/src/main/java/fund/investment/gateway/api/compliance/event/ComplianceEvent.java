package fund.investment.gateway.api.compliance.event;

import fund.investment.basic.common.DomainEvent;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:14 上午
 **/
@NoArgsConstructor
public class ComplianceEvent extends DomainEvent {

    public ComplianceEvent(String id) {
        super(id);
    }
}
