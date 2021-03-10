package fund.investment.app.pledge.repo.server.aggregate.instruction.status;

import fund.investment.app.pledge.repo.api.event.instruction.PRIstrUpdateConfirmedEvt;
import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeInstructionElement;
import fund.investment.basic.instruction.api.command.UpdateConfirmIstrCmd;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.status.UpdateInstructionState;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * @Author dongkw
 * @Date 2021/3/2、9:45 上午
 **/
public class PrUpdateState<T extends PledgeInstructionElement> extends UpdateInstructionState<T> {

    @Override
    public void handle(InstructionAggregate<T> aggregate, UpdateConfirmIstrCmd<T> cmd) {
        PRIstrUpdateConfirmedEvt evt = new PRIstrUpdateConfirmedEvt();
        evt.copyOf(cmd);
        evt.setTradeElement(cmd.getTradeElement());
        AggregateLifecycle.apply(evt);
    }

}
