package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFailIstrCmd extends InstructionCommand {

    private String failCode;

    private String failMsg;

    public CreateFailIstrCmd(String id, TradeType tradeType, String failCode, String failMsg) {
        super(id, tradeType);
        this.failCode = failCode;
        this.failMsg = failMsg;
    }
}
