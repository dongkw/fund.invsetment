package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrUpdateConfirmedEvt<T extends InstructionElement> extends InstructionEvent {

    private T TradeElement;

}
