package fund.investment.gateway.api.compliance.event.instruction;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:02
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrCmplCancelledEvt extends ComplianceEvent {

    private String istrId;
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;

    public IstrCmplCancelledEvt(String id, String istrId) {
        super(id);
        this.istrId = istrId;
    }
}
