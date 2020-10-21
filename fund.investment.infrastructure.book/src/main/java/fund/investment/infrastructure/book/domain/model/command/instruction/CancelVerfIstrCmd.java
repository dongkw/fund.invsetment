package fund.investment.infrastructure.book.domain.model.command.instruction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelVerfIstrCmd extends VerificationCommand {
    @TargetAggregateIdentifier
    private String unitId;
    private String istrId;

}
