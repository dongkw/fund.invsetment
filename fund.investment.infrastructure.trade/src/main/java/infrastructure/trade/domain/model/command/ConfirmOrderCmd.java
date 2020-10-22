package infrastructure.trade.domain.model.command;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConfirmOrderCmd extends OrderCommand{
	public ConfirmOrderCmd(String id, String instructionId, String tradeType) {
		super(id, instructionId, tradeType);
		
	}


}
