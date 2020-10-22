package infrastructure.trade.domain.model.event;

import infrastructure.trade.domain.model.valueobject.Fill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderFilledEvt extends OrderEvent {

    private Fill fill;

    public OrderFilledEvt(String id, String instructionId, String tradeType, Fill fill) {
        super(id, tradeType, instructionId);
        this.fill = fill;
    }
}
