package fund.investment.infrastructure.book.domain.model.event.instruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IstrVerfEvt {

    private String unitId;
    private BigDecimal amount;

}
