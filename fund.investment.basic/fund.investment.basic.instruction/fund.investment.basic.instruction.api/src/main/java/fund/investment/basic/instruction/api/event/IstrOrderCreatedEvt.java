package fund.investment.basic.instruction.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class IstrOrderCreatedEvt extends InstructionEvent {

    private String orderId;

    private long orderQuantity;

//    public IstrOrderCreatedEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String orderId, long orderQuantity) {
//        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
//        this.orderId = orderId;
//        this.orderQuantity = orderQuantity;
//    }
}
