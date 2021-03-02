package fund.investment.gateway.api.approve.event;

import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApproveIstrEvt extends DomainEvent {

    /**
     * 指令审批状态
     */
    private String approveStatus;

    private List<RiskResultResponse> riskInfos;

}
