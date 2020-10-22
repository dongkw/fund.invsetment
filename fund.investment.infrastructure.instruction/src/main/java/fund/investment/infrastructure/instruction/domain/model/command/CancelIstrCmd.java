package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.vo.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CancelIstrCmd extends InstructionCommand {

    private String cancelMsg;

    private String cancelType;

    private List<Order> orders;

    public CancelIstrCmd(String id, TradeType tradeType, String cancelMsg, String cancelType, List<Order> orders) {
        super(id, tradeType);
        this.cancelMsg = cancelMsg;
        this.cancelType = cancelType;
        this.orders = orders;
    }
}
