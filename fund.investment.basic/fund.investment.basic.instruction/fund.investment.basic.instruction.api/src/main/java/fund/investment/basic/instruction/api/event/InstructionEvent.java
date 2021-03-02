package fund.investment.basic.instruction.api.event;

import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class InstructionEvent extends DomainEvent {

    private TradeType tradeType;


}
