package fund.investment.app.pledge.repo.api.command.trade;

import fund.investment.basic.trade.api.command.CancelOrderCmd;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class PRDeleteOrderCmd extends CancelOrderCmd {


    public PRDeleteOrderCmd(String id, String instructionId, String tradeType, String unitId, long cancelQuantity, String userId, String skId, String cLastModifiedId, Date tLastModifiedTime) {
        super(id, instructionId, tradeType, unitId, cancelQuantity, userId, skId, cLastModifiedId, tLastModifiedTime);
    }
}
