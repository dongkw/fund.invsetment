package fund.investment.basic.instruction.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class IstrUpdatedFailedEvt extends InstructionEvent {
    private List<RiskResultResponse> riskRiskInfos;


}
