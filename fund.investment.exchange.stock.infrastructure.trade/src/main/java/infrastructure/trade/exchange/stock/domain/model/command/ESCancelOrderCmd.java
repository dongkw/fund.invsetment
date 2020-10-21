package infrastructure.trade.exchange.stock.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;
import infrastructure.trade.domain.model.command.CancelOrderCmd;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ESCancelOrderCmd extends CancelOrderCmd{
	
	public ESCancelOrderCmd(String id, String instructionId, String tradeType, String unitId, Long cancelQuantity) {
		super(id, instructionId, tradeType, unitId, cancelQuantity);
		
	}

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
}
