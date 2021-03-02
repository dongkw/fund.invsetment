package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.valueobject.TradeElement;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateConfirmIstrCmd<T extends TradeElement> extends InstructionCommand {
    private T tradeElement;
}
