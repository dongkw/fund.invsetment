package fund.investment.infrastructure.compliance.domain.model.event.instruction;

import fund.investment.infrastructure.compliance.domain.model.event.ComplianceEvent;
import lombok.*;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:02
 **/
@Getter
@Setter
@NoArgsConstructor
public class IstrCmplCancelledEvt extends ComplianceEvent {
    private String istrId;

    public IstrCmplCancelledEvt(String id, String istrId) {
        super(id);
        this.istrId = istrId;
    }
}
