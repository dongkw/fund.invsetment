package fund.investment.infrastructure.book.domain.model.event.instruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author dongkw
 * @Date 2020/9/7、9:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapitalCreateEvt {
    private String id;
    private BigDecimal amount;
}
