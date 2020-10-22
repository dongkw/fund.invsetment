package fund.investment.infrastructure.approve.domain.command;

import fund.investment.infrastructure.common.DomainCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

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
