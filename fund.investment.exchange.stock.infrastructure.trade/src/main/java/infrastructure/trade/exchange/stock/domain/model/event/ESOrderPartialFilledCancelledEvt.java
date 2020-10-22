package infrastructure.trade.exchange.stock.domain.model.event;

import infrastructure.trade.domain.model.event.OrderPartialFilledCancelledEvt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ESOrderPartialFilledCancelledEvt extends OrderPartialFilledCancelledEvt{
	
	public ESOrderPartialFilledCancelledEvt(String id, String instructionId, String tradeType, String unitId, Long cancelQuantity) {
		super(id, instructionId, tradeType, unitId, cancelQuantity);
		
	}

}
