package fund.investment.gateway.api.compliance.event.inqresult;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import lombok.Data;

import java.util.List;

@Data
public class InqResultCmplRejectFailedEvt extends ComplianceEvent {
    /**
     * 风控返回结果信息
     */
    private List<RiskResultResponse> riskInfos;

    /**
     * 风控试算是否通过
     */
    private Boolean riskPass;
    /**
     * 风控结果
     */
    private String riskPassStr;
    /**
     * 产品代理键
     */
    private String skProduct;

}
