package infrastructure.trade.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConfirmOrderCmd extends OrderCommand{
	public ConfirmOrderCmd(String id, String instructionId, String tradeType) {
		super(id, instructionId, tradeType);
		
	}

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.content(this)
				.name(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
}
