package fund.investment.instruction.exchange.stock.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCreatedEvt;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ESIstrCreatedEvt extends IstrCreatedEvt {

    public ESIstrCreatedEvt(TradeType tradeType, String id, String unitId, String accountId, String userId, String securityCode, long quantity) {
        super(tradeType, id, unitId, accountId, userId, securityCode, quantity);
    }
}
