package fund.investment.basic.trade.api.event;

import fund.investment.basic.common.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderEvent extends DomainEvent {

    private String instructionId;

    private String tradeType;

    public OrderEvent(String id, String tradeType, String instructionId) {
        super(id);
        this.instructionId = instructionId;
        this.tradeType = tradeType;
    }

}
