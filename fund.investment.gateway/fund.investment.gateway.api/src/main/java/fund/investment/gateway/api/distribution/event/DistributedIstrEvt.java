package fund.investment.gateway.api.distribution.event;

import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DistributedIstrEvt extends DomainEvent {


    /**
     * 指令分发状态
     */
    private String distributeStatus;

    private List<RiskResultResponse> riskInfos;
}
