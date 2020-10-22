package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.approve.domain.command.AprvIstrRejectedCmd;
import fund.investment.infrastructure.distribution.domain.command.DistIstrRejectedCmd;
import fund.investment.infrastructure.instruction.domain.model.command.*;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructionState {

	private InstructionStatus instructionStatus;

	public InstructionState(InstructionStatus instructionStatus) {
		this.instructionStatus = instructionStatus;
	}
	//指令创建
	public void createConfirm(CreateConfirmIstrCmd createConfirmIstrCmd){}
	//指令失败
	public void createFail(CreateFailIstrCmd createFailIstrCmd){}
	//指令审核通过
	public void aprvPass(AprvPassIstrCmd aprvPassIstrCmd){}
	//指令审核拒绝
	public void aprvReject(AprvIstrRejectedCmd aprvIstrRejectedCmd){}
	//指令分发通过
	public void distribute(DistributeIstrCmd distributeIstrCmd){}
	//指令分发拒绝
	public void distReject(DistIstrRejectedCmd distIstrRejectedCmd){}
	//指令取消
	public void cancel(InstructionAggregate instructionAggregate, CancelIstrCmd cancelIstrCmd){}
	//指令取消确认
	public void cancelConfirm(CancelConfIstrCmd cancelConfIstrCmd){}
	//下委托
	public void createOrder(InstructionAggregate instructionAggregate, CreateIstrOrderCmd createIstrOrderCmd){}
	//取消委托
	public void cancelOrder(CancelIstrOrderCmd cancelIstrOrderCmd){}
	//成交回收
	public void receiveFill(InstructionAggregate instructionAggregate, ReceiveIstrFillCmd receiveIstrFillCmd){}

}
