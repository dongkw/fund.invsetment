package fund.investment.basic.instruction.server.aggregate;

import fund.investment.basic.instruction.api.command.*;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructionState<T extends InstructionElement> {


    private InstructionStatus instructionStatus;

    public InstructionState(InstructionStatus instructionStatus) {
        this.instructionStatus = instructionStatus;
    }

    public void handle(InstructionAggregate<T> instructionAggregate, CreateConfirmIstrCmd<T> createConfirmIstrCmd) {
    }

    public void handle(InstructionAggregate<T> instructionAggregate, CreateFailIstrCmd createFailIstrCmd) {
    }

    public void handle(InstructionAggregate<T> instructionAggregate, ApproveIstrCmd approveIstrCmd) {
    }

    public void handle(InstructionAggregate<T> instructionAggregate, DistributeIstrCmd distributeIstrCmd) {
    }


    public void handle(InstructionAggregate<T> instructionAggregate, CancelIstrCmd cancelIstrCmd) {
    }

    public void handle(InstructionAggregate<T> instructionAggregate, CancelConfIstrCmd cancelConfIstrCmd) {
    }

    public void handle(InstructionAggregate<T> instructionAggregate, RollbackCancelIstrCmd cmd) {
    }

    public void handle(InstructionAggregate<T> instructionAggregate, CreateIstrOrderCmd createIstrOrderCmd) {
    }

    public void handle(CancelIstrOrderCmd cancelIstrOrderCmd) {
    }

    public void handle(InstructionAggregate<T> instructionAggregate, ReceiveIstrFillCmd receiveIstrFillCmd) {
    }

    public void handle(InstructionAggregate<T> aggregate, UpdateIstrCmd<T> cmd) {

    }

    public void handle(InstructionAggregate<T> aggregate, UpdateConfirmIstrCmd<T> cmd) {

    }

    public void handle(InstructionAggregate<T> aggregate, UpdateFailIstrCmd cmd) {

    }
}
