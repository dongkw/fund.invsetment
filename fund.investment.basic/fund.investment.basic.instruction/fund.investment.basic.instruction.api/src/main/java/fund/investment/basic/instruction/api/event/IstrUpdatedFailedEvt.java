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

    private InstructionAggregateVO instructionAggregateVO;

    public IstrUpdatedFailedEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, InstructionAggregateVO instructionAggregateVO) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
        this.instructionAggregateVO = instructionAggregateVO;
    }
}
