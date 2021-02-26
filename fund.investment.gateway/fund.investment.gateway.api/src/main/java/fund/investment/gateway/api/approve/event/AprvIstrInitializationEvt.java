package fund.investment.gateway.api.approve.event;

import fund.investment.basic.common.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AprvIstrInitializationEvt extends DomainEvent {

    private String instructionId;

    private int status;

    private String userId;

    private String operatorId;

    public AprvIstrInitializationEvt(String id, String instructionId, int status, String userId, String operatorId) {
        super(id);
        this.instructionId = instructionId;
        this.status = status;
        this.userId = userId;
        this.operatorId = operatorId;
    }
}
