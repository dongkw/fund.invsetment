package fund.investment.gateway.api.compliance.event.instruction;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:02
 **/
@Data
public class IstrCmplSucceedEvt extends ComplianceEvent {

    private String istrId;
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;

    public IstrCmplSucceedEvt(String id, String istrId) {
        super(id);
        this.istrId = istrId;
    }

    public IstrCmplSucceedEvt(String id, String istrId, String chInstrSource, String chSourceKey, String chSourceNo) {
        super(id);
        this.istrId = istrId;
        this.chInstrSource = chInstrSource;
        this.chSourceKey = chSourceKey;
        this.chSourceNo = chSourceNo;
    }
}
