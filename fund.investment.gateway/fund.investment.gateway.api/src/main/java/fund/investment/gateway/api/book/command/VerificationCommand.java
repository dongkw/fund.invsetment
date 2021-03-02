package fund.investment.gateway.api.book.command;

import fund.investment.basic.common.DomainCommand;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/10/10、10:30 上午
 **/
@NoArgsConstructor
public class VerificationCommand extends DomainCommand {

    public VerificationCommand(Long id) {
        setId(id);
    }
}
