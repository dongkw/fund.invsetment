package fund.investment.gateway.api.book.command.instruction;

import fund.investment.gateway.api.book.command.VerificationCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Author dongkw
 * @Date 2020/9/4、17:41
 **/
@Getter
@Setter
@NoArgsConstructor
public class CapitalCreatCmd extends VerificationCommand {

    private BigDecimal amount;


}
