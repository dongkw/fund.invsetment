package fund.investment.infrastructure.compliance.domain.model.command.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/10/12、10:44 上午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelCmplOrderCmd {
    @TargetAggregateIdentifier
    private String securityCode;
    private String orderId;
}
