package infrastructure.trade.exchange.stock.domain.model.command;

import infrastructure.trade.domain.model.command.CancelOrderCmd;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ESCancelOrderCmd extends CancelOrderCmd{
	
	public ESCancelOrderCmd(String id, String instructionId, String tradeType, String unitId, Long cancelQuantity) {
		super(id, instructionId, tradeType, unitId, cancelQuantity);
		
	}
	
}
