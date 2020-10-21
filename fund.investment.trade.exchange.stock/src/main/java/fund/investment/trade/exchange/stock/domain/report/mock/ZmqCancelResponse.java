package fund.investment.trade.exchange.stock.domain.report.mock;

import infrastructure.trade.userinterface.dto.report.CancelResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ZmqCancelResponse extends CancelResponse {
	
	private String id;
	private String instructionId;

}
