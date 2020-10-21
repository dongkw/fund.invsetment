package infrastructure.trade.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import infrastructure.trade.domain.model.valueobject.Fill;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderFilledEvt extends OrderEvent{
	private Fill fill;
	
	public OrderFilledEvt(String id, String instructionId, String tradeType, Fill fill) {
		super(id, tradeType, instructionId);
		
		this.fill = fill;
		
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
