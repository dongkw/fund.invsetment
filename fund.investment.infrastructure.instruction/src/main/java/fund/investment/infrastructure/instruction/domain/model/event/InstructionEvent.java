package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.common.DomainEvent;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructionEvent extends DomainEvent {

    private TradeType tradeType;

    public InstructionEvent(TradeType tradeType, String id) {
        super(id);
        this.tradeType = tradeType;
    }
}
