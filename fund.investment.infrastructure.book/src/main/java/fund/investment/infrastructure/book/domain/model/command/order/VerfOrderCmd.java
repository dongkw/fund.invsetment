package fund.investment.infrastructure.book.domain.model.command.order;

import fund.investment.infrastructure.book.domain.model.command.VerificationCommand;
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
public class VerfOrderCmd extends VerificationCommand {

    private String orderId;

    public VerfOrderCmd(String id, String unitId, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
