package fund.investment.basic.trade.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancellingEvt extends OrderEvent {

    private List<RiskResultResponse> riskInfos;

}
