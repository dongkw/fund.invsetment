package fund.investment.gateway.api.compliance.event.inqresult;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

import java.util.List;

@Data
public class InqResultCmplDraModifyFailedEvt extends ComplianceEvent {
    private List<RiskResultResponse> riskRiskInfos;
}
