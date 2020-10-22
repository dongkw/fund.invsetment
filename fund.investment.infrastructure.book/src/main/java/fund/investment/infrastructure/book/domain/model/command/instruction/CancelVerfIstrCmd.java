package fund.investment.infrastructure.book.domain.model.command.instruction;

import fund.investment.infrastructure.book.domain.model.command.VerificationCommand;
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

    public CancelVerfIstrCmd(String id, String istrId) {
        super(id);
        this.istrId = istrId;
    }
}
