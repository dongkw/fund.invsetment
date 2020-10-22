package fund.investment.infrastructure.compliance.domain.model.command.instruction;

import java.math.BigDecimal;

import fund.investment.infrastructure.compliance.domain.model.command.ComplianceCommand;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:59
 **/
@Getter
@Setter
@NoArgsConstructor
public class CmplIstrCmd extends ComplianceCommand {

    private String istrId;
    private BigDecimal amount;

    public CmplIstrCmd(String id, String istrId, BigDecimal amount) {
        super(id);
        this.istrId = istrId;
        this.amount = amount;
    }
}
