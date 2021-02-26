package fund.investment.basic.trade.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FillConfirmedEvt extends OrderEvent {


    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String skInstr;

    /**
     * 指令成交状态
     */
    private String chInsDealStatus;

    public FillConfirmedEvt(String id, String tradeType, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String skInstr) {
        super(id, tradeType, instructionId);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.skInstr = skInstr;
    }

}
