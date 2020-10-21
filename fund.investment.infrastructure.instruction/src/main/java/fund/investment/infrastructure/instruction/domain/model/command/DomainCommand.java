package fund.investment.infrastructure.instruction.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DomainCommand {
    @TargetAggregateIdentifier
    @ApiModelProperty(value = "指令编号")
    private String id;
    
    @Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
}
