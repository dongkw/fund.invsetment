package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IstrOrderFailedEvt extends InstructionEvent {

    private String orderId;

    private String failMsg;

    public IstrOrderFailedEvt(TradeType tradeType, String id, String orderId, String failMsg) {
        super(tradeType, id);
        this.orderId = orderId;
        this.failMsg = failMsg;
    }
}
