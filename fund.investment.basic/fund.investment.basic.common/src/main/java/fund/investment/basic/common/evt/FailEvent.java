package fund.investment.basic.common.evt;

import fund.investment.common.http.response.risk.RiskResultDtoResponse;
import lombok.Data;

import java.util.List;

@Data
public class FailEvent {
    private String requsetId;
    private List<RiskResultDtoResponse> riskInfos;
}
