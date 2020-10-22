package infrastructure.trade.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPlacingEvt extends OrderEvent{
	private String exchangId;
	
	public OrderPlacingEvt(String id, String instructionId, String tradeType, String exchangId) {
		super(id, tradeType, instructionId);
		
		this.exchangId = exchangId;
	}

}
