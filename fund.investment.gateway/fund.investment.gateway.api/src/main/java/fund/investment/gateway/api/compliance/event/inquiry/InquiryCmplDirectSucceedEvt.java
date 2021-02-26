package fund.investment.gateway.api.compliance.event.inquiry;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

@Data
public class InquiryCmplDirectSucceedEvt extends ComplianceEvent {
    private String istrId;
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;
}
