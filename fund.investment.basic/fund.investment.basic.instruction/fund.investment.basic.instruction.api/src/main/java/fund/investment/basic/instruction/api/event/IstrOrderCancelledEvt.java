package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class IstrOrderCancelledEvt extends InstructionEvent {

    private String orderId;

    private long cancelQuantity;

    public IstrOrderCancelledEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String orderId, long cancelQuantity) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
        this.orderId = orderId;
        this.cancelQuantity = cancelQuantity;
    }
}
