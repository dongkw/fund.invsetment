package fund.investment.infrastructure.book.domain.model.event.instruction;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IstrVerfRollBackedEvt {

    private String unitId;
    private BigDecimal amount;
    private String istrId;

}
