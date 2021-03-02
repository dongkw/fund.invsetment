package fund.investment.gateway.api.compliance.event.instruction;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author dongkw
 * @Date 2020/9/17、15:02
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrCmplFailedEvt extends ComplianceEvent {

    private String istrId;

    private String code;

    private String msg;

    private List<RiskResultResponse> riskRiskInfos;

    public IstrCmplFailedEvt(Long id, String istrId, String code, String msg, List<RiskResultResponse> riskRiskInfos) {
        setId(id);
        this.istrId = istrId;
        this.code = code;
        this.msg = msg;
        this.riskRiskInfos = riskRiskInfos;
    }

    public String getIstrId() {
        return istrId;
    }

    public void setIstrId(String istrId) {
        this.istrId = istrId;
    }

    public List<RiskResultResponse> getRiskRiskInfos() {
        return riskRiskInfos;
    }

    public void setRiskRiskInfos(List<RiskResultResponse> riskRiskInfos) {
        this.riskRiskInfos = riskRiskInfos;
    }

    public IstrCmplFailedEvt(Long id, String istrId) {
        setId(id);
        this.istrId = istrId;
    }
}
