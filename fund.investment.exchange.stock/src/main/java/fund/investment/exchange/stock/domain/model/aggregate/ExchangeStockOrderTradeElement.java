package fund.investment.exchange.stock.domain.model.aggregate;

import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.vo.OrderTradeElement;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ExchangeStockOrderTradeElement extends OrderTradeElement {

	private TradeSide side;

	private String price;

	private Long amount = 0L;

}
