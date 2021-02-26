package fund.investment.gateway.api.book.command.order;

import fund.investment.gateway.api.book.command.VerificationCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:12 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class CancelVerfOrderCmd extends VerificationCommand {

    private String orderId;

    public CancelVerfOrderCmd(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
