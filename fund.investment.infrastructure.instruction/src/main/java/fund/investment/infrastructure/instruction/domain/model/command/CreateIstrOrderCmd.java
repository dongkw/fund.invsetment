package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.valueobject.OrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class CreateIstrOrderCmd extends InstructionCommand {

    private String orderId;

    public CreateIstrOrderCmd(String id, TradeType tradeType, String orderId) {
        super(id, tradeType);
        this.orderId = orderId;
    }

    public abstract OrderTradeElement getOrderTradeElement();
}
