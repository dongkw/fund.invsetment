package fund.investment.trade.exchange.stock.domain.report.mock;

import infrastructure.trade.userinterface.dto.report.CancellationReport;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ZmqCancellationReport extends CancellationReport {

	private String id;
	private String instructionId;
	private Long cancelQuantity;

	public ZmqCancellationReport(String id, String instructionId, Long cancelQuantity) {
		super();
		this.id = id;
		this.instructionId = instructionId;
		this.cancelQuantity = cancelQuantity;
	}

}
