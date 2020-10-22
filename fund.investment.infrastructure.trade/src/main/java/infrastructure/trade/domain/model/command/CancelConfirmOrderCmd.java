package infrastructure.trade.domain.model.command;

import fund.investment.infrastructure.util.LoggerTemplate;
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

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.content(this)
				.name(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
}
