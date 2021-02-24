package fund.investment.basic.common.http.response.pledgerepo;

import fund.investment.common.http.response.risk.RiskResultResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TradeInvestResponse extends TradeInvestDto{
    private List<RiskResultResponse> riskInfos;
}
