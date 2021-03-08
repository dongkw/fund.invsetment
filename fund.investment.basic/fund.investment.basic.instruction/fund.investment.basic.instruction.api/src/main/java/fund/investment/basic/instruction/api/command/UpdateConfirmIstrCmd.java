package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateConfirmIstrCmd<T extends InstructionElement> extends InstructionCommand {

    private T tradeElement;

}
