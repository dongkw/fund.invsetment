package fund.investment.trade.exchange.stock.domain.report.mock;

import infrastructure.trade.userinterface.dto.report.CancelResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ZmqCancelResponse extends CancelResponse {
	
	private String id;
	private String instructionId;
	
	public ZmqCancelResponse(String id, String instructionId) {
		super();
		this.id = id;
		this.instructionId = instructionId;
	}

}
