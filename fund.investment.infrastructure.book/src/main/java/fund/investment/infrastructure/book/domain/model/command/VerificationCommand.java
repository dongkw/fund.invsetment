package fund.investment.infrastructure.book.domain.model.command;

import fund.investment.infrastructure.common.DomainCommand;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:30 上午
 **/
@NoArgsConstructor
public class VerificationCommand extends DomainCommand {
    public VerificationCommand(String id) {
        super(id);
    }
}
