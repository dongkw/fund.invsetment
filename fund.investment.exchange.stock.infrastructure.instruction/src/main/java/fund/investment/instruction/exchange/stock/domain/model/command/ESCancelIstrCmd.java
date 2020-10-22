package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.vo.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class ESCancelIstrCmd extends CancelIstrCmd {

    public ESCancelIstrCmd(String id, TradeType tradeType, String cancelMsg, String cancelType, List<Order> orders) {
        super(id, tradeType, cancelMsg, cancelType, orders);
    }
}
