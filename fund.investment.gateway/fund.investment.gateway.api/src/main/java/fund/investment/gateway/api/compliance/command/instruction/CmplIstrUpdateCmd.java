package fund.investment.gateway.api.compliance.command.instruction;

import fund.investment.gateway.api.compliance.command.ComplianceCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:59
 **/
@Getter
@Setter
@NoArgsConstructor
public class CmplIstrUpdateCmd extends ComplianceCommand {

    private String istrId;

    private BigDecimal amount;

    public CmplIstrUpdateCmd(Long id, String istrId, BigDecimal amount) {
        super(id);
        this.istrId = istrId;
        this.amount = amount;
    }
}
