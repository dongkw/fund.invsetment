package fund.investment.basic.trade.api.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderRejectConfirmedEvt extends OrderEvent {


    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    public OrderRejectConfirmedEvt(String id, String tradeType, String instructionId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, tradeType, instructionId);
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
