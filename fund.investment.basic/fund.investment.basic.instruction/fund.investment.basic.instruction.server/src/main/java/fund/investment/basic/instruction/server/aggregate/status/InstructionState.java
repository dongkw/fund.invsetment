package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.*;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.valueobject.TradeElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructionState<T extends TradeElement> {


    private InstructionStatus instructionStatus;

    public InstructionState(InstructionStatus instructionStatus) {
        this.instructionStatus = instructionStatus;
    }

    public void createConfirm(InstructionAggregate<T> instructionAggregate, CreateConfirmIstrCmd<T> createConfirmIstrCmd) {
    }

    public void createFail(InstructionAggregate<T> instructionAggregate, CreateFailIstrCmd createFailIstrCmd) {
    }

    public void aprvPass(InstructionAggregate<T> instructionAggregate, ApproveIstrCmd approveIstrCmd) {
    }

    public void distribute(InstructionAggregate<T> instructionAggregate, DistributeIstrCmd distributeIstrCmd) {
    }


    public void cancel(InstructionAggregate<T> instructionAggregate, CancelIstrCmd cancelIstrCmd) {
    }

    public void cancelConfirm(InstructionAggregate<T> instructionAggregate, CancelConfIstrCmd cancelConfIstrCmd) {
    }

    public void cancelFail(InstructionAggregate<T> instructionAggregate, RollbackCancelIstrCmd cmd) {
    }

    public void createOrder(InstructionAggregate<T> instructionAggregate, CreateIstrOrderCmd createIstrOrderCmd) {
    }

    public void cancelOrder(CancelIstrOrderCmd cancelIstrOrderCmd) {
    }

    public void receiveFill(InstructionAggregate<T> instructionAggregate, ReceiveIstrFillCmd receiveIstrFillCmd) {
    }

    public void update(InstructionAggregate<T> aggregate, UpdateIstrCmd<T> cmd) {

    }

    public void updateConfirm(InstructionAggregate<T> aggregate, UpdateConfirmIstrCmd<T> cmd) {

    }

    public void updateFail(InstructionAggregate<T> aggregate, UpdateFailIstrCmd cmd) {

    }
}
