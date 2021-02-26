package fund.investment.gateway.api.compliance.command.order;

import fund.investment.gateway.api.compliance.command.ComplianceCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/10/10、2:16 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class CmplOrderCmd extends ComplianceCommand {

    private String orderId;

    public CmplOrderCmd(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
