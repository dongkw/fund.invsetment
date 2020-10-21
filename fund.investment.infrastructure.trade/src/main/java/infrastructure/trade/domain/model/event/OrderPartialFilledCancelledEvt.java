package infrastructure.trade.domain.model.event;

import fund.investment.infrastructure.util.LoggerTemplate;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel("部分成交Event")
@NoArgsConstructor
public class OrderPartialFilledCancelledEvt extends OrderEvent{
	private String unitId;
	private Long cancelQuantity;
	
	public OrderPartialFilledCancelledEvt(String id, String instructionId, String tradeType,String unitId, Long cancelQuantity) {
		super(id, tradeType, instructionId);
		
		this.unitId = unitId;
		this.cancelQuantity = cancelQuantity;
		
	}

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.CONTENT(this)
				.NAME(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
	
}
 