package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IstrCreatedEvt<T extends InstructionElement> extends InstructionEvent {
    private T tradeElement;

}
