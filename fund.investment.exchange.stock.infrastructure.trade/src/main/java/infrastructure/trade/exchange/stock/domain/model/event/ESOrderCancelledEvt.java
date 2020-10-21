package infrastructure.trade.exchange.stock.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import infrastructure.trade.domain.model.event.OrderCancelledEvt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESOrderCancelledEvt extends OrderCancelledEvt{
	
	public ESOrderCancelledEvt(String id, String instructionId, String tradeType, String unitId, Long cancelQuantity) {
		super(id, instructionId, tradeType, unitId, cancelQuantity);
		
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
