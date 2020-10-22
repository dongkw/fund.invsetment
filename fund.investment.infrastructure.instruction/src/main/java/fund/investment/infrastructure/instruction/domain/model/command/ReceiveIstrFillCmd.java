package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiveIstrFillCmd extends InstructionCommand {

    private String orderId;

    private String fillId;

    private long fillQuantity;

    public ReceiveIstrFillCmd(String id, TradeType tradeType, String orderId, String fillId, long fillQuantity) {
        super(id, tradeType);
        this.orderId = orderId;
        this.fillId = fillId;
        this.fillQuantity = fillQuantity;
    }
}
