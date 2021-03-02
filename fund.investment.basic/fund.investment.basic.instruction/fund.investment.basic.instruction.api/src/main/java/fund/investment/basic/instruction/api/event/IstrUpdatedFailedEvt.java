package fund.investment.basic.instruction.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.valueobject.InstructionAggregateVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class IstrUpdatedFailedEvt extends InstructionEvent {
    private List<RiskResultResponse> riskRiskInfos;


}
