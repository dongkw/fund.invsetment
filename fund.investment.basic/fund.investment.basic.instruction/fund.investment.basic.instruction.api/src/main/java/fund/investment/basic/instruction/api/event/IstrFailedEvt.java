package fund.investment.basic.instruction.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class IstrFailedEvt extends InstructionEvent {

    private String failCode;

    private String failMsg;

    private List<RiskResultResponse> riskRiskInfos;

    public IstrFailedEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String failCode, String failMsg) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
        this.failCode = failCode;
        this.failMsg = failMsg;
    }
}
