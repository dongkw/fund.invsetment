package fund.investment.infrastructure.book.domain.model.event.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:14 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVerfCancelledEvt {
    private String unitId;
    private String orderId;
}
