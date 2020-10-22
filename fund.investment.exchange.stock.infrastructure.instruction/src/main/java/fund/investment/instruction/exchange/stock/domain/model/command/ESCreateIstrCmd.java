package fund.investment.instruction.exchange.stock.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESCreateIstrCmd extends CreateIstrCmd {

    private String price;

    private TradeSide side;

    private long amount;

    public ESCreateIstrCmd(String id, TradeType tradeType, String unitId, String accountId, String userId, String securityCode, long quantity, String price, TradeSide side, Long amount) {
        super(id, tradeType, unitId, accountId, userId, securityCode, quantity);
        this.price = price;
        this.side = side;
        this.amount = amount;
    }
}
