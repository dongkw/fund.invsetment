package fund.investment.infrastructure.book.domain.model.command.instruction;

import fund.investment.infrastructure.book.domain.model.command.VerificationCommand;
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
public class FrozenAmountCmd extends VerificationCommand {

    private BigDecimal amount;

    private String istrId;

    public FrozenAmountCmd(String id, BigDecimal amount, String istrId) {
        super(id);
        this.amount = amount;
        this.istrId = istrId;
    }
}
