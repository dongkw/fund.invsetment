package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ESCancelIstrOrderCmd extends CancelIstrOrderCmd {

    public ESCancelIstrOrderCmd(String id, TradeType tradeType, String orderId, long cancelQuantity) {
        super(id, tradeType, orderId, cancelQuantity);
    }
}
