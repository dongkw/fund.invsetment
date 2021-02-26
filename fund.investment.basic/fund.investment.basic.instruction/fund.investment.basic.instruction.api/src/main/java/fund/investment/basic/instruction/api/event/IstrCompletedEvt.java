package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class IstrCompletedEvt extends InstructionEvent {

    /**
     * 指令成交状态
     */
    private String chInsDealStatus;

    public IstrCompletedEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String chInsDealStatus) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
        this.chInsDealStatus = chInsDealStatus;
    }
}
