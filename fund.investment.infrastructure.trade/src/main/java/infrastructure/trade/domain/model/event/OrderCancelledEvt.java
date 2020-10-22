package infrastructure.trade.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCancelledEvt extends OrderEvent{
	private String unitId;
	private Long cancelQuantity;
	
	public OrderCancelledEvt(String id, String instructionId, String tradeType, String unitId, Long cancelQuantity) {
		super(id, tradeType, instructionId);
		
		this.unitId = unitId;
		this.cancelQuantity = cancelQuantity;
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
