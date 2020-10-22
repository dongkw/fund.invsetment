package fund.investment.infrastructure.instruction.domain.model.event;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IstrCreatedEvt extends InstructionEvent {

    private String unitId;

    private String accountId;

    private String userId;

    private String securityCode;

    private long quantity;

    public IstrCreatedEvt(TradeType tradeType, String id, String unitId, String accountId, String userId, String securityCode, long quantity) {
        super(tradeType, id);
        this.unitId = unitId;
        this.accountId = accountId;
        this.userId = userId;
        this.securityCode = securityCode;
        this.quantity = quantity;
    }
}
