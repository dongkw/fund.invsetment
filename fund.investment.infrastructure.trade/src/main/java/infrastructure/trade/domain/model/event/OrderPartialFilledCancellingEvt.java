package infrastructure.trade.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPartialFilledCancellingEvt extends OrderEvent {

    public OrderPartialFilledCancellingEvt(String id, String instructionId, String tradeType) {
        super(id, tradeType, instructionId);
    }
}
