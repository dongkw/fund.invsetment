package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveIstrFillCmd extends InstructionCommand {

    private String orderId;

    private String fillId;

    private long fillQuantity;

    /**
     * 指令成交状态
     */
    private String chInsDealStatus;

    public ReceiveIstrFillCmd(TradeType tradeType, String skId, String userId, String skInstr, String chLastModifiedId, Date tsLastModifiedTime, String orderId, String fillId, long fillQuantity, String chInsDealStatus) {
        super(tradeType, skId, userId, skInstr, chLastModifiedId, tsLastModifiedTime);
        this.orderId = orderId;
        this.fillId = fillId;
        this.fillQuantity = fillQuantity;
        this.chInsDealStatus = chInsDealStatus;
    }
}
