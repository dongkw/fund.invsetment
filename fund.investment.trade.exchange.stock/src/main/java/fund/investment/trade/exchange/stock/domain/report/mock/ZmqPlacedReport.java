package fund.investment.trade.exchange.stock.domain.report.mock;

import infrastructure.trade.userinterface.dto.report.PlacedReport;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)	
public class ZmqPlacedReport extends PlacedReport {
	
	private String id;
	private String instructionId;

}
