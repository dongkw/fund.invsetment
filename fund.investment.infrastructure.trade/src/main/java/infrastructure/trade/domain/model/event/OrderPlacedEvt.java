package infrastructure.trade.domain.model.event;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPlacedEvt extends OrderEvent{
	private LocalDateTime orderTime;
	
	public OrderPlacedEvt(String id, String instructionId, String tradeType) {
		super(id, tradeType, instructionId);
		
		this.orderTime = LocalDateTime.now();
	}

}
