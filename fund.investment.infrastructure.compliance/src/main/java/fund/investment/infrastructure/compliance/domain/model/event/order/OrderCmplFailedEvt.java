package fund.investment.infrastructure.compliance.domain.model.event.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/10/10、2:22 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCmplFailedEvt {
    private String securityCode;
    private String orderId;
}
