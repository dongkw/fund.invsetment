package fund.investment.gateway.api.compliance.command.inqresult;

import fund.investment.gateway.api.compliance.command.ComplianceCommand;
import lombok.Data;

@Data
public class BackCmplInqResultCmd extends ComplianceCommand {
    private String chSourceKey;
    private String chMemo;
}
