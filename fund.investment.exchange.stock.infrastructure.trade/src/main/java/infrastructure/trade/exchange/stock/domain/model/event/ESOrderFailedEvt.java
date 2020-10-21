package infrastructure.trade.exchange.stock.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import infrastructure.trade.domain.model.event.OrderFailedEvt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESOrderFailedEvt extends OrderFailedEvt{
	
	public ESOrderFailedEvt(String id, String instructionId, String tradeType, String unitId, String failCode, String failMsg) {
		super(id, instructionId, tradeType, unitId, failCode, failMsg);
		
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
