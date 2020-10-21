package fund.investment.infrastructure.compliance.domain.model.command.instruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelComplIstrCmd extends ComplianceCommand {
    @TargetAggregateIdentifier
    private String securityCode;
    private String istrId;
}
