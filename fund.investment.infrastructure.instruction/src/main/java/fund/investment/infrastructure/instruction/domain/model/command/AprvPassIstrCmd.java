package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AprvPassIstrCmd extends InstructionCommand {

    public AprvPassIstrCmd(String id, TradeType tradeType) {
        super(id, tradeType);
    }
}
