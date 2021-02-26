package fund.investment.basic.trade.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FillConfirmingSuccessEvt extends OrderEvent{

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String skInstr;

    /**
     * 指令序号
     **/
    private String insSourceNo;
    /**
     * 指令证券号
     **/
    private String insSourceKey;

    /**
     * 指令来源主键ID:存对应系统的唯一指令的主键
     */
    private String chSourceKey;

    /**
     * 指令来源编号1:存对应系统的指令序号
     */
    private String chSourceNo;

    public FillConfirmingSuccessEvt(String id, String tradeType, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String skInstr) {
        super(id, tradeType, instructionId);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.skInstr = skInstr;
    }

}
