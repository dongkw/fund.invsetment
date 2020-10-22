package infrastructure.trade.domain.model.command;

import infrastructure.trade.domain.model.valueobject.Fill;
import infrastructure.trade.domain.model.valueobject.OrderTradeElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FillOrderCmd extends OrderCommand {

	private Fill fill;
	private OrderTradeElement orderTradeElement;
	
	public FillOrderCmd(String id, String instructionId, String tradeType, Fill fill) {
		super(id, instructionId, tradeType);
		
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