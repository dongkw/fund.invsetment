package fund.investment.gateway.api.book.event.order;

import fund.investment.gateway.api.book.event.VerificationEvent;
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
public class OrderVerfFailedEvt extends VerificationEvent {

    private String orderId;

    public OrderVerfFailedEvt(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}