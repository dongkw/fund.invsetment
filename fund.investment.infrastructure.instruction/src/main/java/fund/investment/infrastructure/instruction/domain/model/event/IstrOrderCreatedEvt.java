package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IstrOrderCreatedEvt extends InstructionEvent {

    private String orderId;

    private long orderQuantity;

    public IstrOrderCreatedEvt(TradeType tradeType, String id, String orderId, long orderQuantity) {
        super(tradeType, id);
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
    }
}
