package fund.investment.gateway.api.book.event;

import fund.investment.basic.common.DomainEvent;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:32 上午
 **/
@NoArgsConstructor
public class VerificationEvent extends DomainEvent {

    public VerificationEvent(Long id) {
        setId(id);
    }
}
