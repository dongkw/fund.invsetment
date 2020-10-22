package infrastructure.trade.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCompletedEvt extends OrderEvent{

	public OrderCompletedEvt(String id, String instructionId, String tradeType) {
		super(instructionId, tradeType, instructionId);
		
	}

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.content(this)
				.name(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
}
