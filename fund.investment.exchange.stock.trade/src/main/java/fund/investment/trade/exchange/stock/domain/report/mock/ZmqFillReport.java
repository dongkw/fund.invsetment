package fund.investment.trade.exchange.stock.domain.report.mock;

import fund.investment.trade.exchange.stock.domain.model.entity.ExchangeStockFill;
import infrastructure.trade.userinterface.dto.report.FillReport;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 芳军
 *
 */
@Getter
@Setter
public class ZmqFillReport extends FillReport {
	
	private String id;
	private ExchangeStockFill fill;

	@Override
	public String toString() {
		return "ZmqFillReport [id=" + id + ", fill=" + fill + "]";
	}
	
}
