package fund.investment.basic.trade.api.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FailOrderCmd extends OrderCommand {

    private String unitId;

    private String failCode;

    private String failMsg;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String chCancelReason;

    public FailOrderCmd(String id, String instructionId, String tradeType, String unitId, String failCode, String failMsg, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String chCancelReason) {
        super(id, instructionId, tradeType);
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