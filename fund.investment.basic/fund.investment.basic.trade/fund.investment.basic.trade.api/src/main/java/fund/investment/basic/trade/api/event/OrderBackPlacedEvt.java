package fund.investment.basic.trade.api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderBackPlacedEvt extends OrderEvent {

    private String unitId;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    private String skOriginId;

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


    public OrderBackPlacedEvt(String id, String tradeType, String instructionId, String unitId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, tradeType, instructionId);
        this.unitId = unitId;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
