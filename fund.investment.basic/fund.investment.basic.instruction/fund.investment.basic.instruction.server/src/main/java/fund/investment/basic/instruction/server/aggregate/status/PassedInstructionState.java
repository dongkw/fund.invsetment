package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.command.DistributeIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrCancellingEvt;
import fund.investment.basic.instruction.api.event.IstrPendingEvt;
import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.InstructionState;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PassedInstructionState<T extends InstructionElement> extends InstructionState<T> {

    public PassedInstructionState() {
        super(InstructionStatus.APRV_PASS);
    }

    @Override
    public void handle(InstructionAggregate<T> instructionAggregate, DistributeIstrCmd distributeIstrCmd) {
        IstrPendingEvt istrPendingEvt = new IstrPendingEvt();
        istrPendingEvt.copyOf(distributeIstrCmd);
        istrPendingEvt.setChInsDispStatus(distributeIstrCmd.getChInsDispStatus());
        AggregateLifecycle.apply(istrPendingEvt);
    }

    @Override
    public void handle(InstructionAggregate<T> aggregate, CancelIstrCmd cancelIstrCmd) {
        IstrCancellingEvt istrCancellingEvt = new IstrCancellingEvt();
        istrCancellingEvt.copyOf(cancelIstrCmd);
        istrCancellingEvt.setRiskInfos(cancelIstrCmd.getRiskInfos());
        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
