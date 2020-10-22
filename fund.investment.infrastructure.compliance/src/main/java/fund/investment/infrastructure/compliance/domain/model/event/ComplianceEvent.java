package fund.investment.infrastructure.compliance.domain.model.event;

import fund.investment.infrastructure.common.DomainEvent;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:14 上午
 **/
public class ComplianceEvent extends DomainEvent {
    public ComplianceEvent(String id) {
        super(id);
    }
}
