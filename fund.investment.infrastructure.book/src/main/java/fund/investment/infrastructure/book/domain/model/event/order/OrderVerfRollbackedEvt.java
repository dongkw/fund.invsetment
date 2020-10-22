package fund.investment.infrastructure.book.domain.model.event.order;

import fund.investment.infrastructure.book.domain.model.event.VerificationEvent;
import lombok.*;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:14 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class OrderVerfRollbackedEvt extends VerificationEvent {
    private String orderId;

    public OrderVerfRollbackedEvt(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
