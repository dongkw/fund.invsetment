package infrastructure.trade.exchange.stock.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import infrastructure.trade.domain.model.event.OrderFilledEvt;
import infrastructure.trade.domain.model.valueobject.Fill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESOrderFilledEvt extends OrderFilledEvt{
	public ESOrderFilledEvt(String id, String instructionId, String tradeType, Fill fill) {
		super(id, instructionId, tradeType, fill);
		
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
