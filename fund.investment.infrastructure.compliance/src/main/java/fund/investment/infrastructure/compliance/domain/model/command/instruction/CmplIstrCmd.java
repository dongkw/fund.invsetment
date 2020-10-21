package fund.investment.infrastructure.compliance.domain.model.command.instruction;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:59
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmplIstrCmd extends ComplianceCommand {
    @TargetAggregateIdentifier
    private String securityCode;
    private String istrId;
    private BigDecimal amount;
}
