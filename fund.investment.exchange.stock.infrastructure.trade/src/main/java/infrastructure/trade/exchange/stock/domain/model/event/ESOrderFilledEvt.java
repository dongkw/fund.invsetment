package infrastructure.trade.exchange.stock.domain.model.event;

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

}
