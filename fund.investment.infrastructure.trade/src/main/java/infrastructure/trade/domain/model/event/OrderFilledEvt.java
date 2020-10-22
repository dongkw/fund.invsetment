package infrastructure.trade.domain.model.event;

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
