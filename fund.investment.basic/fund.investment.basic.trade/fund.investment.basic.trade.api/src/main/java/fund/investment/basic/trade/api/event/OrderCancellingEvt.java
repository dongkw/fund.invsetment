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
public class OrderCancellingEvt extends OrderEvent {

    private String unitId;
    private String skInstr;
    private long cancelQuantity;
    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String instrSkInstr;
    /**
     * 指令来源主键ID:存对应系统的唯一指令的主键
     */
    private String chSourceKey;

    /**
     * 指令来源编号1:存对应系统的指令序号
     */
    private String chSourceNo;
    /**
     * 指令来源1:增加数据字典7043：1、系统指令 2、总行指令 3、O32指令 4、方达指令
     */
    private String chInstrSource;

    public OrderCancellingEvt(String id, String tradeType, String instructionId, String unitId, long cancelQuantity, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, tradeType, instructionId);
        this.unitId = unitId;
        this.cancelQuantity = cancelQuantity;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
