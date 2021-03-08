package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIstrCmd<T extends InstructionElement> extends InstructionCommand {

    private T tradeElement;

}
