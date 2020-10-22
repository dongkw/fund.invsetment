package fund.investment.infrastructure.book.domain.model.command.instruction;

import java.math.BigDecimal;

import fund.investment.infrastructure.book.domain.model.command.VerificationCommand;
import lombok.*;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:06
 **/
@Getter
@Setter
@NoArgsConstructor
public class RollbackVerfIstrCmd extends VerificationCommand {

    private BigDecimal amount;
    private String istrId;

    public RollbackVerfIstrCmd(String id, String unitId, BigDecimal amount, String istrId) {
        super(id);
        this.amount = amount;
        this.istrId = istrId;
    }
}
