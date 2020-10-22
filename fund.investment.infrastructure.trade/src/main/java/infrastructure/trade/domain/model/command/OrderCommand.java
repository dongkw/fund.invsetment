package infrastructure.trade.domain.model.command;

import fund.investment.infrastructure.common.DomainCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommand extends DomainCommand {

    private String instructionId;

    private String tradeType;

    public OrderCommand(String id, String instructionId, String tradeType) {
        super(id);
        this.instructionId = instructionId;
        this.tradeType = tradeType;
    }
}
