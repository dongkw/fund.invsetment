package fund.investment.infrastructure.book.domain.model.command.order;

import fund.investment.infrastructure.book.domain.model.command.VerificationCommand;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/10/10、3:12 下午
 **/
@Getter
@Setter
@NoArgsConstructor
public class RollbackVerfOrderCmd extends VerificationCommand {

    private String orderId;

    public RollbackVerfOrderCmd(String id, String orderId) {
        super(id);
        this.orderId = orderId;
    }
}
