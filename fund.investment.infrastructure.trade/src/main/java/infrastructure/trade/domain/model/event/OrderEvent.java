package infrastructure.trade.domain.model.event;

import fund.investment.infrastructure.common.DomainEvent;
import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderEvent extends DomainEvent {
	private String instructionId;
	private String tradeType;
	
	public OrderEvent(String id, String tradeType, String instructionId) {
		super(id);
		
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
