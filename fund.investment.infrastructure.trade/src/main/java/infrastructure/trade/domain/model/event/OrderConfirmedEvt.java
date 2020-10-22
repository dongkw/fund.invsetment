package infrastructure.trade.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderConfirmedEvt extends OrderEvent{
	
	public OrderConfirmedEvt(String id, String instructionId, String tradeType) {
		super(id, tradeType, instructionId);
		
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
