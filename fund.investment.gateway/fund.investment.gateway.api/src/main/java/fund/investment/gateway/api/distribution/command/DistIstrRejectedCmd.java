package fund.investment.gateway.api.distribution.command;

import fund.investment.basic.common.DomainCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DistIstrRejectedCmd extends DomainCommand {

    private String instructionId;

    private String userId;

    private String operatorId;

    public DistIstrRejectedCmd(String id, String instructionId, String userId, String operatorId) {
        super(id);
        this.instructionId = instructionId;
        this.userId = userId;
        this.operatorId = operatorId;
    }
}