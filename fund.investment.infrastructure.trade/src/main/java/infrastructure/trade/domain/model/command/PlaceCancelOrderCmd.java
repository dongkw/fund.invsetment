package infrastructure.trade.domain.model.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceCancelOrderCmd extends OrderCommand {
	private String exchangeId;
	
	public PlaceCancelOrderCmd(String id, String instructionId, String tradeType, String exchangeId) {
		super(id, instructionId, tradeType);
		
		this.exchangeId = exchangeId;
		
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
