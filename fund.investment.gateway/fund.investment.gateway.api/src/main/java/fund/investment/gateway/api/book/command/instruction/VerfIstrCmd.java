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
public class VerfIstrCmd extends VerificationCommand {

    private String istrId;

    private BigDecimal amount;

    public VerfIstrCmd(String id, String istrId, BigDecimal amount) {
        super(id);
        this.istrId = istrId;
        this.amount = amount;
    }
}
