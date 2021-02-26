package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DistributeIstrCmd extends InstructionCommand {

    /**
     * 指令分发状态
     */
    private String chInsDispStatus;

    public DistributeIstrCmd(TradeType tradeType, String skId, String userId, String skInstr, String chLastModifiedId, Date tsLastModifiedTime, String chInsDispStatus) {
        super(tradeType, skId, userId, skInstr, chLastModifiedId, tsLastModifiedTime);
        this.chInsDispStatus = chInsDispStatus;
    }
}
