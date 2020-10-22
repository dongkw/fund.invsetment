package fund.investment.infrastructure.distribution.domain.command;

import fund.investment.infrastructure.common.DomainCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DistributedIstrInitCmd extends DomainCommand {
    private String instructionId;

    private String userId;

    private String operatorId;

    public DistributedIstrInitCmd(String id, String instructionId, String userId, String operatorId) {
        super(id);
        this.instructionId = instructionId;
        this.userId = userId;
        this.operatorId = operatorId;
    }
}
