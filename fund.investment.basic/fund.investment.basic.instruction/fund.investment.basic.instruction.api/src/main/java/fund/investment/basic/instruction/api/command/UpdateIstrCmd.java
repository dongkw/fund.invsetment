package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIstrCmd<T extends InstructionElement> extends InstructionCommand {
    private T tradeElement;
}
