package infrastructure.trade.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCancellingEvt extends OrderEvent{
	
	public OrderCancellingEvt(String id, String instructionId, String tradeType) {
		super(instructionId, tradeType, instructionId);
		
	}

}
