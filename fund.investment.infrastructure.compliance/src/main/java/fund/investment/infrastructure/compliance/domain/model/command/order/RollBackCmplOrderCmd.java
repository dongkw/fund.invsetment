package fund.investment.infrastructure.compliance.domain.model.command.order;

import fund.investment.infrastructure.compliance.domain.model.command.ComplianceCommand;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/10/10、2:16 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class RollBackCmplOrderCmd extends ComplianceCommand {

    private String orderId;

    public RollBackCmplOrderCmd(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
