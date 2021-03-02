package fund.investment.basic.instruction.server.aggregate.status;


import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.command.DistributeIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrCancellingEvt;
import fund.investment.basic.instruction.api.event.IstrPendingEvt;
import fund.investment.basic.instruction.api.valueobject.TradeElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PassedInstructionState<T extends TradeElement> extends InstructionState<T> {

    public PassedInstructionState() {
        super(InstructionStatus.APRV_PASS);
    }

    @Override
    public void distribute(InstructionAggregate<T> instructionAggregate, DistributeIstrCmd distributeIstrCmd) {
        IstrPendingEvt istrPendingEvt = new IstrPendingEvt();
        istrPendingEvt.copyOf(distributeIstrCmd);
        istrPendingEvt.setChInsDispStatus(distributeIstrCmd.getChInsDispStatus());
        AggregateLifecycle.apply(istrPendingEvt);
    }

    @Override
    public void cancel(InstructionAggregate<T> aggregate, CancelIstrCmd cancelIstrCmd) {
        IstrCancellingEvt istrCancellingEvt = new IstrCancellingEvt();
        istrCancellingEvt.copyOf(cancelIstrCmd);
        istrCancellingEvt.setRiskInfos(cancelIstrCmd.getRiskInfos());
        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
