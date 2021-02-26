package fund.investment.basic.trade.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 报价发送失败事件
 */
@Getter
@Setter
@NoArgsConstructor
public class PlacingOrderFailedEvt extends OrderFailedEvt {

    public PlacingOrderFailedEvt(String id, String tradeType, String instructionId, String unitId, String failCode, String failMsg, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String chCancelReason) {
        super(id, tradeType, instructionId, unitId, failCode, failMsg, userId, skId, chLastModifiedId, tsLastModifiedTime, chCancelReason);
    }
}
