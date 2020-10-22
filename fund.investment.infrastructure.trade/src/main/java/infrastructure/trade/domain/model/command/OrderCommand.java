package infrastructure.trade.domain.model.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import fund.investment.infrastructure.common.DomainCommand;
import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderCommand extends DomainCommand {

	@NonNull
	@TargetAggregateIdentifier
	private String id;
	private String instructionId;
	private String tradeType;
	
	public OrderCommand(String id, String instructionId, String tradeType) {
		this.id = id;
		this.instructionId = instructionId;
		this.tradeType = tradeType;
	}

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
}
