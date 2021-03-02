package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.valueobject.TradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IstrUpdateConfirmedEvt<T extends TradeElement> extends InstructionEvent {

    private T TradeElement;

}
