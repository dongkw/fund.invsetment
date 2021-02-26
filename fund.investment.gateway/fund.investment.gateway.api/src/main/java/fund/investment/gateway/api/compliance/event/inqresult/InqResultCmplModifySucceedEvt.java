package fund.investment.gateway.api.compliance.event.inqresult;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

@Data
public class InqResultCmplModifySucceedEvt extends ComplianceEvent {
    private Boolean success;

    private Boolean passWarn;
}
