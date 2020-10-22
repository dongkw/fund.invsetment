package infrastructure.trade.domain.model.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CancelConfirmOrderCmd extends OrderCommand{
	private String unitId;
	private Long cancelQuantity;
	
	public CancelConfirmOrderCmd(String id, String instructionId, String tradeType, String unitId, Long cancelQuantity) {
		super(id, instructionId, tradeType);
		
		this.unitId = unitId;
		this.cancelQuantity = cancelQuantity;
	}

}
