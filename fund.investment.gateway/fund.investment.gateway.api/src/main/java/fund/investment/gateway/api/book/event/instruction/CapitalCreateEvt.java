package fund.investment.gateway.api.book.event.instruction;

import fund.investment.gateway.api.book.event.VerificationEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}
