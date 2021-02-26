package fund.investment.basic.trade.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderPlacedEvt extends OrderEvent{


    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String chReportNo;
    private String chCancelReason;
    private String instrSkId;

    private String insSourceKey;

    private String insSourceNo;

    private String skOriginId;

    /**
     * 委托状态:数据字典：7172
     */
    private String chEntrustStatus;
    /**
     * 报价状态:数据字典：7184
     */
    private String chOfferStatus;

    public OrderPlacedEvt(String id, String tradeType, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime, String chReportNo, String chCancelReason) {
        super(id, tradeType, instructionId);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
        this.chReportNo = chReportNo;
        this.chCancelReason = chCancelReason;
    }

}
