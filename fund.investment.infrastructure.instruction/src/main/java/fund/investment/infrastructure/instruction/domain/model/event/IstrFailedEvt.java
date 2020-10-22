package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IstrFailedEvt extends InstructionEvent {

    private String failCode;

    private String failMsg;

    public IstrFailedEvt(TradeType tradeType, String id, String failCode, String failMsg) {
        super(tradeType, id);
        this.failCode = failCode;
        this.failMsg = failMsg;
    }
}
