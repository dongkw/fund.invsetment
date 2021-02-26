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
public class OrderAutoCancelMatchedEvt extends OrderEvent {

    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;
    private String instrSkId;
    private String userId;

    public OrderAutoCancelMatchedEvt(String id, String tradeType, String instructionId) {
        super(id, tradeType, instructionId);
    }
}
