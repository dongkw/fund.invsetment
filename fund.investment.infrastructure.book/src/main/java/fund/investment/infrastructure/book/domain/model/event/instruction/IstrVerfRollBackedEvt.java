package fund.investment.infrastructure.book.domain.model.event.instruction;

import java.math.BigDecimal;

import fund.investment.infrastructure.book.domain.model.event.VerificationEvent;
import lombok.*;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:06
 **/
@Getter
@Setter
@NoArgsConstructor
public class IstrVerfRollBackedEvt extends VerificationEvent {

    private BigDecimal amount;
    private String istrId;

    public IstrVerfRollBackedEvt(String id, BigDecimal amount, String istrId) {
        super(id);
        this.amount = amount;
        this.istrId = istrId;
    }
}
