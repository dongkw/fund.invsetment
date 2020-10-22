package fund.investment.infrastructure.distribution.domain.event;

import fund.investment.infrastructure.common.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DistIstrRejectedEvt extends DomainEvent {

    private String instructionId;

    private int status;

    private String userId;

    private String operatorId;

    public DistIstrRejectedEvt(String id, String instructionId, int status, String userId, String operatorId) {
        super(id);
        this.instructionId = instructionId;
        this.status = status;
        this.userId = userId;
        this.operatorId = operatorId;
    }
}
