package fund.investment.gateway.api.book.command.instruction;

import fund.investment.gateway.api.book.command.VerificationCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:06
 **/
@Getter
@Setter
@NoArgsConstructor
public class CancelVerfIstrCmd extends VerificationCommand {

    private String istrId;


}
