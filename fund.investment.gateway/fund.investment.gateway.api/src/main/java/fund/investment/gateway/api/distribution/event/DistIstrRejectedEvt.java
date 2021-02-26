package fund.investment.gateway.api.distribution.event;

import fund.investment.basic.common.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DistIstrRejectedEvt extends DomainEvent {

    private String instructionId;

    private int status;

    private String userId;

    private String operatorId;

    private Date operatorTime;

    public DistIstrRejectedEvt(String id, String instructionId, int status, String userId, String operatorId, Date operatorTime) {
        super(id);
        this.instructionId = instructionId;
        this.status = status;
        this.userId = userId;
        this.operatorId = operatorId;
        this.operatorTime = operatorTime;
    }
}
