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

    public void createConfirm(CreateConfirmIstrCmd createConfirmIstrCmd) {
    }

    public void createFail(CreateFailIstrCmd createFailIstrCmd) {
    }

    public void aprvPass(AprvPassIstrCmd aprvPassIstrCmd) {
    }

    public void aprvReject(AprvIstrRejectedCmd aprvIstrRejectedCmd) {
    }

    public void distribute(DistributeIstrCmd distributeIstrCmd) {
    }

    public void distReject(DistIstrRejectedCmd distIstrRejectedCmd) {
    }

    public void cancel(InstructionAggregate instructionAggregate, CancelIstrCmd cancelIstrCmd) {
    }

    public void cancelConfirm(CancelConfIstrCmd cancelConfIstrCmd) {
    }

    public void createOrder(InstructionAggregate instructionAggregate, CreateIstrOrderCmd createIstrOrderCmd) {
    }

    public void cancelOrder(CancelIstrOrderCmd cancelIstrOrderCmd) {
    }

    public void receiveFill(InstructionAggregate instructionAggregate, ReceiveIstrFillCmd receiveIstrFillCmd) {
    }
}
