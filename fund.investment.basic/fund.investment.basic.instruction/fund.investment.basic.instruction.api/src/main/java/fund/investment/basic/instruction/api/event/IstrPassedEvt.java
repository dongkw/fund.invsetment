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


}
