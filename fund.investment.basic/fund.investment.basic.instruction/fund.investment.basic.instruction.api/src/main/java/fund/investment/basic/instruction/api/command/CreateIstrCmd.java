package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.valueobject.TradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CreateIstrCmd<T extends TradeElement> extends InstructionCommand {

    private T tradeElement;

}
