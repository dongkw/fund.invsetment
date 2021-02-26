package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class IstrExecutingEvt extends InstructionEvent {

    public IstrExecutingEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime) {
        super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
    }
}
