package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IstrCreatedEvt<T extends TradeElement> extends InstructionEvent {
    private T tradeElement;

}
