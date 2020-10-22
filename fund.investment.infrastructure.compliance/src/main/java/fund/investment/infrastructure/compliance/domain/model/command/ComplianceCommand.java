package fund.investment.infrastructure.compliance.domain.model.command;

import fund.investment.infrastructure.common.DomainCommand;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:12 上午
 **/
public class ComplianceCommand extends DomainCommand {
    public ComplianceCommand(String id) {
        super(id);
    }
}
