package fund.investment.app.pledge.repo.api.event.trade;

import fund.investment.basic.trade.api.event.OrderFilledEvt;
import fund.investment.basic.trade.api.valueobject.Fill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PROrderFilledEvt extends OrderFilledEvt {

    public PROrderFilledEvt(String id, String tradeType, String instructionId, String userId, String skId, String cLastModifiedId, Date tLastModifiedTime, Fill fill) {
        super(id, tradeType, instructionId, userId, skId, cLastModifiedId, tLastModifiedTime, fill);
    }
}
