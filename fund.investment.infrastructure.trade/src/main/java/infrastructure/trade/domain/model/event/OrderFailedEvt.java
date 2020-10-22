package infrastructure.trade.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderFailedEvt extends OrderEvent{
	private String unitId;
	private String failCode;
	private String failMsg;
	
	public OrderFailedEvt(String id, String instructionId, String tradeType, String unitId, String failCode, String failMsg) {
		super(instructionId, tradeType, instructionId);
		
		this.unitId = unitId;
		this.failCode = failCode;
		this.failMsg = failMsg;
		
	}


}
