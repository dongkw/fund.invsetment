package fund.investment.gateway.api.book.event.instruction;

import fund.investment.gateway.api.book.event.VerificationEvent;
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
public class IstrVerfRollBackedEvt extends VerificationEvent {

    private BigDecimal amount;

    private String istrId;

    public IstrVerfRollBackedEvt(String id, BigDecimal amount, String istrId) {
        super(id);
        this.amount = amount;
        this.istrId = istrId;
    }
}
