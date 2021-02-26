package fund.investment.gateway.api.compliance.event.inquiry;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

@Data
public class InquiryCmplCancelSucceedEvt  extends ComplianceEvent {

    private String istrId;


}
