package fund.investment.infrastructure.compliance.domain.model.event.instruction;

import fund.investment.infrastructure.compliance.domain.model.event.ComplianceEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:15
 **/
@Getter
@Setter
@NoArgsConstructor
public class ComplianceSaveEvt extends ComplianceEvent {

    private String istrId;

    public ComplianceSaveEvt(String id, String istrId) {
        super(id);
        this.istrId = istrId;
    }
}
