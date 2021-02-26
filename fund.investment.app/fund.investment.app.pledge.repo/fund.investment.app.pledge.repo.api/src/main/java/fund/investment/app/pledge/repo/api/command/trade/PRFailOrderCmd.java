package fund.investment.app.pledge.repo.api.command.trade;

import fund.investment.basic.trade.api.command.FailOrderCmd;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class PRFailOrderCmd extends FailOrderCmd {

    public PRFailOrderCmd(String id, String instructionId, String tradeType, String unitId, String failCode, String failMsg, String userId, String skId, String cLastModifiedId, Date tLastModifiedTime, String cCancelReason) {
        super(id, instructionId, tradeType, unitId, failCode, failMsg, userId, skId, cLastModifiedId, tLastModifiedTime, cCancelReason);
    }
}
