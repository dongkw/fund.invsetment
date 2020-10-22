package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateConfirmIstrCmd extends InstructionCommand {

    public CreateConfirmIstrCmd(String id, TradeType tradeType) {
        super(id, tradeType);
    }
}
