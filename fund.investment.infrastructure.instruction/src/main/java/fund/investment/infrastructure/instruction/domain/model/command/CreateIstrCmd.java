package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateIstrCmd extends InstructionCommand {

    private String unitId;

    private String accountId;

    private String userId;

    private String securityCode;

    private long quantity;

    public CreateIstrCmd(String id, TradeType tradeType, String unitId, String accountId, String userId, String securityCode, long quantity) {
        super(id, tradeType);
        this.unitId = unitId;
        this.accountId = accountId;
        this.userId = userId;
        this.securityCode = securityCode;
        this.quantity = quantity;
    }
}
