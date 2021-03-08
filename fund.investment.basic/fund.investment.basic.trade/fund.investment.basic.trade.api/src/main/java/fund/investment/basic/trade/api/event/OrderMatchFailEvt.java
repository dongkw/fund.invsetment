package fund.investment.basic.trade.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderMatchFailEvt extends OrderEvent {

    private String failCode;

    private String failMsg;

    private List<RiskResultResponse> riskInfos;
}
