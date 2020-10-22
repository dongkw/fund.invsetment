package infrastructure.trade.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreatedEvt extends OrderEvent {

    private String unitId;

    private String accountId;

    private String userId;

    public OrderCreatedEvt(String id, String instructionId, String tradeType, String unitId, String accountId, String userId) {
        super(id, tradeType, instructionId);
        this.unitId = unitId;
        this.accountId = accountId;
        this.userId = userId;
    }
}
