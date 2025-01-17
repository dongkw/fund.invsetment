package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class IstrConfirmedEvt<T extends InstructionElement> extends InstructionEvent {

    private T tradeElement;
}
