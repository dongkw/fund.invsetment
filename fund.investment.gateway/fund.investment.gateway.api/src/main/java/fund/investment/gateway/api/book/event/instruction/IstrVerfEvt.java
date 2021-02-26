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
public class IstrVerfEvt extends VerificationEvent {

    private BigDecimal amount;

    public IstrVerfEvt(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
