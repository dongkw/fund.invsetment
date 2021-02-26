package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.*;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructionState {

    private InstructionStatus instructionStatus;

    public InstructionState(InstructionStatus instructionStatus) {
        this.instructionStatus = instructionStatus;
    }

    public void createConfirm(InstructionAggregate instructionAggregate, CreateConfirmIstrCmd createConfirmIstrCmd) {
    }

    public void createFail(InstructionAggregate instructionAggregate, CreateFailIstrCmd createFailIstrCmd) {
    }

    public void aprvPass(InstructionAggregate instructionAggregate, ApproveIstrCmd approveIstrCmd) {
    }

    public void aprvReject(ApproveIstrCmd aprvIstrRejectedCmd) {
    }

    public void distribute(InstructionAggregate instructionAggregate, DistributeIstrCmd distributeIstrCmd) {
    }

    public void distReject(DistributeIstrCmd distIstrRejectedCmd) {
    }

    public void cancel(InstructionAggregate instructionAggregate, CancelIstrCmd cancelIstrCmd) {
    }

    public void cancelConfirm(InstructionAggregate instructionAggregate, CancelConfIstrCmd cancelConfIstrCmd) {
    }

    public void createOrder(InstructionAggregate instructionAggregate, CreateIstrOrderCmd createIstrOrderCmd) {
    }

    public void cancelOrder(CancelIstrOrderCmd cancelIstrOrderCmd) {
    }

    public void receiveFill(InstructionAggregate instructionAggregate, ReceiveIstrFillCmd receiveIstrFillCmd) {
    }
}
