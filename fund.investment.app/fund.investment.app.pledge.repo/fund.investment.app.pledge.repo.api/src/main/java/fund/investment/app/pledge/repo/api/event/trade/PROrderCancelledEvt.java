package fund.investment.app.pledge.repo.api.event.trade;

import fund.investment.basic.trade.api.event.OrderCancelledEvt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PROrderCancelledEvt extends OrderCancelledEvt {

    public PROrderCancelledEvt(String id, String tradeType, String instructionId, String unitId, long cancelQuantity, String userId, String skId, String cLastModifiedId, Date tLastModifiedTime) {
        super(id, tradeType, instructionId, unitId, cancelQuantity, userId, skId, cLastModifiedId, tLastModifiedTime);
    }
}
