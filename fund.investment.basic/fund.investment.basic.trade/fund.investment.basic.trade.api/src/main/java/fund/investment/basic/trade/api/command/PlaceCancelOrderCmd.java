package fund.investment.basic.trade.api.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PlaceCancelOrderCmd extends OrderCommand {

    private String exchangeId;

    private String userId;
    private String skId;
    private String chLastModifiedId;
    private Date tsLastModifiedTime;

    public PlaceCancelOrderCmd(String id, String instructionId, String tradeType, String exchangeId, String userId, String skId, String chLastModifiedId, Date tsLastModifiedTime) {
        super(id, instructionId, tradeType);
        this.exchangeId = exchangeId;
        this.userId = userId;
        this.skId = skId;
        this.chLastModifiedId = chLastModifiedId;
        this.tsLastModifiedTime = tsLastModifiedTime;
    }

}
