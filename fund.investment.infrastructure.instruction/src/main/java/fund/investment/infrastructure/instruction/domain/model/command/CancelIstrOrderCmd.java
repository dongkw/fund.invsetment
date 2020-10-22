package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CancelIstrOrderCmd extends InstructionCommand {

    private String orderId;

    private long cancelQuantity;

    public CancelIstrOrderCmd(String id, TradeType tradeType, String orderId, long cancelQuantity) {
        super(id, tradeType);
        this.orderId = orderId;
        this.cancelQuantity = cancelQuantity;
    }
}
