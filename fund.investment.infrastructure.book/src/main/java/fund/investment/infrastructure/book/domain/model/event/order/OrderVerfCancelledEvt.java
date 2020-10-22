package fund.investment.infrastructure.book.domain.model.event.order;

import fund.investment.infrastructure.book.domain.model.event.VerificationEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:14 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class OrderVerfCancelledEvt extends VerificationEvent {

    private String orderId;

    public OrderVerfCancelledEvt(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
