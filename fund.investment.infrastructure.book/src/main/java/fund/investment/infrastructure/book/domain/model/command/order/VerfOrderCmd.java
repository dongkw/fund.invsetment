package fund.investment.infrastructure.book.domain.model.command.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:12 下午
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerfOrderCmd {
    @TargetAggregateIdentifier
    private String unitId;
    private String orderId;
}
