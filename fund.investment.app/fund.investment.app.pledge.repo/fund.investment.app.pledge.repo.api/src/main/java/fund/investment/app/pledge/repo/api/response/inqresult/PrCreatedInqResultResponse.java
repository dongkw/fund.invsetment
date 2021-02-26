package fund.investment.app.pledge.repo.api.response.inqresult;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Data;

import java.util.List;

@Data
public class PrCreatedInqResultResponse {
    private Boolean success;
    private Boolean passWarn;
    private List<RiskResultResponse> riskInfos;
    private Boolean riskPass;
    private String riskPassStr;
    private String skProduct;
}
