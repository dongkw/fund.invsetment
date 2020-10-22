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
<<<<<<< HEAD

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.content(this)
				.name(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
=======
>>>>>>> 3a0a2561e0fa4da06e959b868de6b818fed652af
	
}