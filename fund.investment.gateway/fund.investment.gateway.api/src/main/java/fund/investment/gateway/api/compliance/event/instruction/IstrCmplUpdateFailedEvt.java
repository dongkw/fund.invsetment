package fund.investment.gateway.api.compliance.event.instruction;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.gateway.api.compliance.event.ComplianceEvent;
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
public class IstrCmplUpdateFailedEvt extends ComplianceEvent {

    private String istrId;

    private String code;

    private String msg;

    private List<RiskResultResponse> riskRiskInfos;

    public IstrCmplUpdateFailedEvt(Long id, String istrId) {
        setId(id);
        this.istrId = istrId;
    }

    public IstrCmplUpdateFailedEvt(Long id, String istrId, String code, String msg, List<RiskResultResponse> riskRiskInfos) {
        setId(id);
        this.istrId = istrId;
        this.code = code;
        this.msg = msg;
        this.riskRiskInfos = riskRiskInfos;
    }

}
