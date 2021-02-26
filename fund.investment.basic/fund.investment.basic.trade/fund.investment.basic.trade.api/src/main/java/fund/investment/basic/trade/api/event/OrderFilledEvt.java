package fund.investment.basic.trade.api.event;

import fund.investment.basic.trade.api.valueobject.Fill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilledEvt extends OrderEvent {

    private String userId;
    private String skId;
    private String skInstr;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    private Fill fill;

    private String instrSkId;

    private String insSourceKey;

    private String insSourceNo;

    private String skOriginId;

    /**
     * 委托状态:数据字典：7172
     */
    private String chEntrustStatus;

    public OrderFilledEvt(String id, String tradeType, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, Fill fill) {
        super(id, tradeType, instructionId);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.fill = fill;
    }

}
