package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IstrPendingEvt extends InstructionEvent {

    public IstrPendingEvt(TradeType tradeType, String id) {
        super(tradeType, id);
    }
}
