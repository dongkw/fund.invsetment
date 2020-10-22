package infrastructure.trade.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPartialFilledCancelledEvt extends OrderEvent {

    private String unitId;

    private Long cancelQuantity;

    public OrderPartialFilledCancelledEvt(String id, String instructionId, String tradeType, String unitId, Long cancelQuantity) {
        super(id, tradeType, instructionId);
        this.unitId = unitId;
        this.cancelQuantity = cancelQuantity;
    }
}
 