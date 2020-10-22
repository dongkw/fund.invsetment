package infrastructure.trade.exchange.stock.domain.model.command;

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

}
