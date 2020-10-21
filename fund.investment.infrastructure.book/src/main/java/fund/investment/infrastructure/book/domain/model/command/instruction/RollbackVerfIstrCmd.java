package fund.investment.infrastructure.book.domain.model.command.instruction;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

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
public class RollbackVerfIstrCmd extends VerificationCommand {
    @TargetAggregateIdentifier
    private String unitId;
    private BigDecimal amount;
    private String istrId;

}
