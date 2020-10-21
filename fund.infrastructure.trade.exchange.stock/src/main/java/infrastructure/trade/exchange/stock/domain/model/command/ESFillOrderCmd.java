package infrastructure.trade.exchange.stock.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;
import infrastructure.trade.domain.model.command.FillOrderCmd;
import infrastructure.trade.domain.model.valueobject.Fill;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ESFillOrderCmd extends FillOrderCmd {
//	private ExchangeStockFill exchangeStockFill;
	
	public ESFillOrderCmd(String id, String instructionId, String tradeType, Fill fill) {
		super(id, instructionId, tradeType, fill);
		
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
