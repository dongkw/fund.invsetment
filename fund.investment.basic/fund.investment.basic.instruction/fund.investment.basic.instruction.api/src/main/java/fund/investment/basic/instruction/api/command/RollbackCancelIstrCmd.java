package fund.investment.basic.instruction.api.command;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author dongkw
 * @Date 2020/10/10、9:29 上午
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RollbackCancelIstrCmd extends InstructionCommand {

    private String cancelType;

    private String cancelMsg;

    private String chInstrSource;

    private String chSourceKey;

    private String chSourceNo;

    public RollbackCancelIstrCmd(TradeType tradeType, String id, String skId, String skInstr) {
        super(tradeType, id, skId, skInstr);
    }
}
