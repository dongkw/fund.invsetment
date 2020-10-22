package fund.investment.infrastructure.approve.domain.command;

import fund.investment.infrastructure.common.DomainCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AprvIstrRejectedCmd extends DomainCommand {

    private String instructionId;

    private String userId;

    private String operatorId;

    public AprvIstrRejectedCmd(String id, String instructionId, String userId, String operatorId) {
        super(id);
        this.instructionId = instructionId;
        this.userId = userId;
        this.operatorId = operatorId;
    }
}
