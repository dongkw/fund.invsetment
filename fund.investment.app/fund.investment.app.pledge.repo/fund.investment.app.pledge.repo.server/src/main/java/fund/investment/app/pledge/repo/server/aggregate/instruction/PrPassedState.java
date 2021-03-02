package fund.investment.app.pledge.repo.server.aggregate.instruction;


import fund.investment.app.pledge.repo.api.event.instruction.PRIstrCancellingEvt;
import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeTradeElement;
import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.status.PassedInstructionState;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PrPassedState<T extends PledgeTradeElement> extends PassedInstructionState<T> {


    @Override
    public void cancel(InstructionAggregate<T> aggregate, CancelIstrCmd cancelIstrCmd) {
        PRIstrCancellingEvt istrCancellingEvt = new PRIstrCancellingEvt();
        istrCancellingEvt.copyOf(cancelIstrCmd);
        istrCancellingEvt.setRiskInfos(cancelIstrCmd.getRiskInfos());

        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
