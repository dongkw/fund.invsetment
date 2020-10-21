package fund.investment.infrastructure.book.domain.model.event.instruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IstrVerfCancelledEvt extends VerificationEvent {
    private String unitId;
    private String istrId;
}
