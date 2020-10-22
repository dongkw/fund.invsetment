package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IstrOrderCancelledEvt extends InstructionEvent {

    private String orderId;

    private long cancelQuantity;

    public IstrOrderCancelledEvt(TradeType tradeType, String id, String orderId, long cancelQuantity) {
        super(tradeType, id);
        this.orderId = orderId;
        this.cancelQuantity = cancelQuantity;
    }
}
