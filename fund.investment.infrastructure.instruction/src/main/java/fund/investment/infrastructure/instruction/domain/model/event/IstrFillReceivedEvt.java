package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IstrFillReceivedEvt extends InstructionEvent {

    private String orderId;

    private String fillId;

    private long fillQuantity;

    public IstrFillReceivedEvt(TradeType tradeType, String id, String orderId, String fillId, long fillQuantity) {
        super(tradeType, id);
        this.orderId = orderId;
        this.fillId = fillId;
        this.fillQuantity = fillQuantity;
    }
}
