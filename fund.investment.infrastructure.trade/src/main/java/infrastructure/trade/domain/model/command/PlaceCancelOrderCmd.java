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

}
