package fund.investment.trade.exchange.stock.domain.report.mock;

import infrastructure.trade.userinterface.dto.report.PlaceResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ZmpPlaceResponse extends PlaceResponse {
	
	private String id;
	private String instructionId;
	
	public ZmpPlaceResponse(String id, String instructionId) {
		super();
		this.id = id;
		this.instructionId = instructionId;
	}
	
}
