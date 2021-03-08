package fund.investment.basic.trade.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderFailedEvt extends OrderEvent {

    private String failCode;

    private String failMsg;

    private List<RiskResultResponse> riskInfos;

}
