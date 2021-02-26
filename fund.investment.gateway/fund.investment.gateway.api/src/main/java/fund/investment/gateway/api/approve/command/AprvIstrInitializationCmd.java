package fund.investment.gateway.api.approve.command;

import fund.investment.basic.common.DomainCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AprvIstrInitializationCmd extends DomainCommand {

    private String instructionId;

    private String userId;

    private String operatorId;

    public AprvIstrInitializationCmd(String id, String instructionId, String userId, String operatorId) {
        super(id);
        this.instructionId = instructionId;
        this.userId = userId;
        this.operatorId = operatorId;
    }
}
