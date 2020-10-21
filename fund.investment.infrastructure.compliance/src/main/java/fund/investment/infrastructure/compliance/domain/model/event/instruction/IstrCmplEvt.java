package fund.investment.infrastructure.compliance.domain.model.event.instruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:59
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IstrCmplEvt {
    @TargetAggregateIdentifier
    private String securityCode;
    private long total;

}
