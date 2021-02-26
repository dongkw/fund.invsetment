package fund.investment.gateway.api.compliance.event.inqresult;

import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

@Data
public class InqResultCmplCommitSucceedEvt extends ComplianceEvent {
    private Boolean success;

    private Boolean passWarn;
}
