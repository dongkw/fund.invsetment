package fund.investment.infrastructure.book.domain.model.event;

import fund.investment.infrastructure.common.DomainEvent;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:32 上午
 **/
public class VerificationEvent extends DomainEvent {
    public VerificationEvent(String id) {
        super(id);
    }
}
