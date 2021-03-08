package fund.investment.basic.trade.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderCancelledEvt extends OrderEvent {

    private List<RiskResultResponse> riskInfos;

}
