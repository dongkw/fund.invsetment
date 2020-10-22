package fund.investment.infrastructure.compliance.domain.model.command.order;

import fund.investment.infrastructure.compliance.domain.model.command.ComplianceCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/10/12、10:44 上午
 **/
@Getter
@Setter
@NoArgsConstructor
public class CancelCmplOrderCmd extends ComplianceCommand {

    private String orderId;

    public CancelCmplOrderCmd(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
