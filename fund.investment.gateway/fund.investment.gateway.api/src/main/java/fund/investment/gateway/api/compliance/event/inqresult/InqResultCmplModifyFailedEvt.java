package fund.investment.gateway.api.compliance.event.inqresult;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.gateway.api.compliance.event.ComplianceEvent;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class InqResultCmplModifyFailedEvt extends ComplianceEvent {
    /**
     * 风控返回结果信息
     */
    @ApiModelProperty(value = "询价结果下达风控条款", example = "", notes = "询价结果通用参数", required = true)
    private List<RiskResultResponse> riskInfos;

}
