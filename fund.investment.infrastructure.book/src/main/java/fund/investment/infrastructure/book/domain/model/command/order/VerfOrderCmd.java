package fund.investment.infrastructure.book.domain.model.command.order;

import fund.investment.infrastructure.common.DomainCommand;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:12 下午
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerfOrderCmd extends DomainCommand {
    @TargetAggregateIdentifier
    private String unitId;
    private String orderId;
}
