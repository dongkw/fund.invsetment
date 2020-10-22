package fund.investment.infrastructure.compliance.domain.model.command.instruction;

import fund.investment.infrastructure.compliance.domain.model.command.ComplianceCommand;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:15
 **/
@Getter
@Setter
@NoArgsConstructor
public class RollbackCmplIstrCmd extends ComplianceCommand {

    private String istrId;

    public RollbackCmplIstrCmd(String id, String istrId) {
        super(id);
        this.istrId = istrId;
    }
}
