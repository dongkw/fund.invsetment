package fund.investment.basic.instruction.api.event;

import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class IstrPassedEvt extends InstructionEvent{


	/**
	 * 指令审批状态
	 */
	private String chFlowApproveStatus;


	public IstrPassedEvt(String id, Long requestId, TradeType tradeType, String userId, String skId, String skInstr, String cLastModifiedId, Date tLastModifiedTime, String chFlowApproveStatus) {
		super(id, requestId, tradeType, userId, skId, skInstr, cLastModifiedId, tLastModifiedTime);
		this.chFlowApproveStatus = chFlowApproveStatus;
	}
}
