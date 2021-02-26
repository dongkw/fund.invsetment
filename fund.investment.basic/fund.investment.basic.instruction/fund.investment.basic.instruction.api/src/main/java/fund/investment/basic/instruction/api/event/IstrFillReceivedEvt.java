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
public class IstrFillReceivedEvt extends InstructionEvent {

    private String orderId;

    private String fillId;

    private long fillQuantity;

    /**
     * 指令成交状态
     */
    private String chInsDealStatus;

    public IstrFillReceivedEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String orderId, String fillId, long fillQuantity, String chInsDealStatus) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
        this.orderId = orderId;
        this.fillId = fillId;
        this.fillQuantity = fillQuantity;
        this.chInsDealStatus = chInsDealStatus;
    }
}
