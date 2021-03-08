package fund.investment.basic.trade.api.event;


import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCancelEvt extends OrderEvent {
    private List<RiskResultResponse> riskInfos;
}
