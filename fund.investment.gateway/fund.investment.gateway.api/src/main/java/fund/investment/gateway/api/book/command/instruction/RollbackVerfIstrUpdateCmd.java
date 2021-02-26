package fund.investment.gateway.api.book.command.instruction;

import fund.investment.gateway.api.book.command.VerificationCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:06
 **/
@Getter
@Setter
@NoArgsConstructor
public class RollbackVerfIstrUpdateCmd extends VerificationCommand {

    private BigDecimal amount;

    private String istrId;

    public RollbackVerfIstrUpdateCmd(String id, BigDecimal amount, String istrId) {
        super(id);
        this.amount = amount;
        this.istrId = istrId;
    }
}
