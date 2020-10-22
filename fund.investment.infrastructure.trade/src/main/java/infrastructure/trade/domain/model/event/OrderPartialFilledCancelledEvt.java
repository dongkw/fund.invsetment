package infrastructure.trade.domain.model.event;

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
<<<<<<< HEAD

	@Override
	public String toString() {
		return LoggerTemplate.builder()
				.content(this)
				.name(this.getClass().getSimpleName())
				.build()
				.toJson();
	}
=======
>>>>>>> 3a0a2561e0fa4da06e959b868de6b818fed652af
	
}
 