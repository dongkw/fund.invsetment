package fund.investment.infrastructure.approve.domain.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.context.annotation.Profile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Profile("command")
@ApiModel(value = "审批初始化")
public class AprvIstrInitializationCmd {
	@ApiModelProperty(value = "审批id")
	@TargetAggregateIdentifier
	private String id;
	@ApiModelProperty(value = "指令id")
	private String instructionId;
	private String userId;
	private String operatorId;
}
