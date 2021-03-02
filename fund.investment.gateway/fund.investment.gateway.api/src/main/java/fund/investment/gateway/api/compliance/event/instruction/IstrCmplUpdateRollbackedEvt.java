package fund.investment.gateway.api.compliance.event.instruction;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
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
public class IstrCmplUpdateRollbackedEvt extends ComplianceEvent {

    private String istrId;

    public IstrCmplUpdateRollbackedEvt(Long id, String istrId) {
        setId(id);
        this.istrId = istrId;
    }
}
