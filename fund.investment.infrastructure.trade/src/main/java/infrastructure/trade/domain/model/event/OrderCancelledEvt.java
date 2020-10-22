package infrastructure.trade.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCancelledEvt extends OrderEvent {

    private String unitId;

    private long cancelQuantity;

    public OrderCancelledEvt(String id, String instructionId, String tradeType, String unitId, long cancelQuantity) {
        super(id, tradeType, instructionId);
        this.unitId = unitId;
        this.cancelQuantity = cancelQuantity;
    }
}
