package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.common.DomainCommand;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructionCommand extends DomainCommand {

    private TradeType tradeType;

    public InstructionCommand(String id, TradeType tradeType) {
        super(id);
        this.tradeType = tradeType;
    }
}
