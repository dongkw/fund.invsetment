package fund.investment.gateway.api.compliance.event.inquiry;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

import java.util.List;

@Data
public class InquiryCmplDirectFailEvt extends ComplianceEvent {

    private String istrId;
    private List<RiskResultResponse> riskRiskInfos;
}
