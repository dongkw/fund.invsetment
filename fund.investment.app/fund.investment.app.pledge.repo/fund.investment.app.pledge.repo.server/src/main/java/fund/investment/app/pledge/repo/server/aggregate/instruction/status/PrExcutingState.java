package fund.investment.app.pledge.repo.server.aggregate.instruction.status;

import fund.investment.app.pledge.repo.api.event.instruction.PRIstrCancellingEvt;
import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeInstructionElement;
import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.status.ExcutingInstructionState;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PrExcutingState<T extends PledgeInstructionElement> extends ExcutingInstructionState<T> {

    @Override
    public void handle(InstructionAggregate<T> aggregate, CancelIstrCmd cancelIstrCmd) {
        PRIstrCancellingEvt istrCancellingEvt = new PRIstrCancellingEvt();
        istrCancellingEvt.copyOf(cancelIstrCmd);
        istrCancellingEvt.setRiskInfos(cancelIstrCmd.getRiskInfos());

        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
