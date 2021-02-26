package fund.investment.gateway.api.compliance.command;

import fund.investment.basic.common.DomainCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:12 上午
 **/
@NoArgsConstructor
@Data
public class ComplianceCommand extends DomainCommand {

    private String userId;
    public ComplianceCommand(String id) {
        super(id);
    }
}
