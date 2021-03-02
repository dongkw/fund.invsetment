package fund.investment.basic.instruction.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IstrFailedEvt extends InstructionEvent {


    private List<RiskResultResponse> riskInfos;


}
