package fund.investment.infrastructure.book.domain.model.event.instruction;

import fund.investment.infrastructure.book.domain.model.event.VerificationEvent;
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
public class FrozenAmountEvt extends VerificationEvent {

    private BigDecimal amount;

    public FrozenAmountEvt(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
