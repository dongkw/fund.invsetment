package infrastructure.trade.domain.model.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderCmd extends OrderCommand {

    private String unitId;

    private String accountId;

    private String userId;

    public CreateOrderCmd(String id, String instructionId, String tradeType, String unitId, String accountId, String userId) {
        super(id, instructionId, tradeType);
        this.unitId = unitId;
        this.accountId = accountId;
        this.userId = userId;
    }
}
