package fund.investment.infrastructure.compliance.domain.model.event.instruction;

import fund.investment.infrastructure.compliance.domain.model.event.ComplianceEvent;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:59
 **/
@Getter
@Setter
@NoArgsConstructor
public class IstrCmplEvt extends ComplianceEvent {

    private long total;

    public IstrCmplEvt(String id, long total) {
        super(id);
        this.total = total;
    }
}
