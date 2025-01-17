package fund.investment.gateway.api.compliance.command.instruction;

import fund.investment.gateway.api.compliance.command.ComplianceCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:15
 **/
@Getter
@Setter
@NoArgsConstructor
public class ComplianceSaveCmd extends ComplianceCommand {

    private String istrId;

    public ComplianceSaveCmd(Long id, String istrId) {
        super(id);
        this.istrId = istrId;
    }
}
