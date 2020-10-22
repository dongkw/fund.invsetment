package fund.investment.infrastructure.book.domain.model.event.instruction;

import fund.investment.infrastructure.book.domain.model.event.VerificationEvent;
import lombok.*;

import java.math.BigDecimal;

/**
 * @Author dongkw
 * @Date 2020/9/7、9:05
 **/
@Getter
@Setter
@NoArgsConstructor
public class CapitalCreateEvt extends VerificationEvent {

    private BigDecimal amount;

    public CapitalCreateEvt(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
