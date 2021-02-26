package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.valueobject.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancelIstrCmd extends InstructionCommand {

    private String cancelMsg;

    private String cancelType;

    private List<Order> orders;

    private String chInstrSource;

    private String chSourceKey;

    private String chSourceNo;

    public CancelIstrCmd(TradeType tradeType, String id, String skId, String skInstr) {
        super(tradeType, id, skId, skInstr);
    }
}
