package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ApproveIstrCmd extends InstructionCommand {

    /**
     * 指令审批状态
     */
    private String chFlowApproveStatus;

    public ApproveIstrCmd(TradeType tradeType, String skId, String userId, String skInstr, String chLastModifiedId, Date tsLastModifiedTime, String chFlowApproveStatus) {
        super(tradeType, skId, userId, skInstr, chLastModifiedId, tsLastModifiedTime);
        this.chFlowApproveStatus = chFlowApproveStatus;
    }
}
