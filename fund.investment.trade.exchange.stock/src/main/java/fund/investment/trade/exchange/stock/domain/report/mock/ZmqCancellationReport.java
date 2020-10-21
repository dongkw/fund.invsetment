package fund.investment.trade.exchange.stock.domain.report.mock;

import infrastructure.trade.userinterface.dto.report.CancellationReport;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ZmqCancellationReport extends CancellationReport {
	
	private String id;
	private String instructionId;
	private Long cancelQuantity;

}
