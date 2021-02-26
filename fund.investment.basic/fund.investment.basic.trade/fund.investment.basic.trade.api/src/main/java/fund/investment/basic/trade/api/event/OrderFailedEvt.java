package fund.investment.basic.trade.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderFailedEvt extends OrderEvent {

    private String unitId;

    private String failCode;

    private String failMsg;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String chCancelReason;

    public OrderFailedEvt(String id, String tradeType, String instructionId, String unitId, String failCode, String failMsg, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String chCancelReason) {
        super(id, tradeType, instructionId);
        this.unitId = unitId;
        this.failCode = failCode;
        this.failMsg = failMsg;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.chCancelReason = chCancelReason;
    }

}
