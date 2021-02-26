package fund.investment.gateway.api.distribution.event;

import fund.investment.basic.common.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DistIstrInitilazationEvt extends DomainEvent {

    private String instructionId;

    private Integer status;

    private String userId;

    private String operatorId;

    public DistIstrInitilazationEvt(String id, String instructionId, Integer status, String userId, String operatorId) {
        super(id);
        this.instructionId = instructionId;
        this.status = status;
        this.userId = userId;
        this.operatorId = operatorId;
    }
}
