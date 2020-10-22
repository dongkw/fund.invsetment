package infrastructure.trade.domain.model.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CancelOrderCmd extends OrderCommand {
	private String unitId;
	private Long cancelQuantity;
	
	public CancelOrderCmd(String id, String instructionId, String tradeType, String unitId, Long cancelQuantity) {
		super(id, instructionId, tradeType);
		this.unitId = unitId;
		this.cancelQuantity = cancelQuantity;
		
	}
	
}
