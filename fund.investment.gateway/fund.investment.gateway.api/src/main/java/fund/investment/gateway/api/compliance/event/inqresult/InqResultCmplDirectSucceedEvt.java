package fund.investment.gateway.api.compliance.event.inqresult;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

@Data
public class InqResultCmplDirectSucceedEvt extends ComplianceEvent {
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;
}
