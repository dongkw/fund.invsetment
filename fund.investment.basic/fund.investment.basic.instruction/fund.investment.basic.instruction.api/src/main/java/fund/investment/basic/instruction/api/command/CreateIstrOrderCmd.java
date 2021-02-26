package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.valueobject.OrderTradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CreateIstrOrderCmd extends InstructionCommand {

    private String orderId;

    public CreateIstrOrderCmd(TradeType tradeType, String id, String skId, String skInstr) {
        super(tradeType, id, skId, skInstr);
    }

    public abstract OrderTradeElement getOrderTradeElement();
}