package fund.investment.infrastructure.compliance.domain.model.command.instruction;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RollbackCmplIstrCmd extends ComplianceCommand {
    @TargetAggregateIdentifier
    private String securityCode;
    private String istrId;
}
