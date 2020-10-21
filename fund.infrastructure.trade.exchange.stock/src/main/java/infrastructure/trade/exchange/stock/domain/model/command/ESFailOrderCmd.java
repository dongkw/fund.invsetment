package infrastructure.trade.exchange.stock.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;
import infrastructure.trade.domain.model.command.FailOrderCmd;

public class ESFailOrderCmd extends FailOrderCmd{

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
}
