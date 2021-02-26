package fund.investment.basic.trade.api.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CancelConfirmOrderCmd extends OrderCommand {

    private String unitId;

    private long cancelQuantity;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    private String skOriginId;

    /**
     * 委托状态:数据字典：7172
     */
    private String chEntrustStatus;

    public CancelConfirmOrderCmd(String id, String instructionId, String tradeType, String unitId, long cancelQuantity, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, instructionId, tradeType);
        this.unitId = unitId;
        this.cancelQuantity = cancelQuantity;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
