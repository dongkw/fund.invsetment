package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DistributeIstrCmd extends InstructionCommand {

    public DistributeIstrCmd(String id, TradeType tradeType) {
        super(id, tradeType);
    }
}
