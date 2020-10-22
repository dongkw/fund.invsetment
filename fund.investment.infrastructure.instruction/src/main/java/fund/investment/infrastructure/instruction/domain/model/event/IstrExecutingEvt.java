package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IstrExecutingEvt extends InstructionEvent {

    public IstrExecutingEvt(TradeType tradeType, String id) {
        super(tradeType, id);
    }
}
