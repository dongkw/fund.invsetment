package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.NoArgsConstructor;

/**
 * @Author dongkw
 * @Date 2020/10/10、9:29 上午
 **/

@NoArgsConstructor
public class CancelConfIstrCmd extends InstructionCommand {

    public CancelConfIstrCmd(String id, TradeType tradeType) {
        super(id, tradeType);
    }
}
