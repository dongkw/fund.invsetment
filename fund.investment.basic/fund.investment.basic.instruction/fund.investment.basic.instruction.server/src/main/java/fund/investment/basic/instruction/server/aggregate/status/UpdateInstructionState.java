package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.UpdateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.UpdateFailIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrFailedEvt;
import fund.investment.basic.instruction.api.event.IstrUpdateConfirmedEvt;
import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.InstructionState;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class UpdateInstructionState<T extends InstructionElement> extends InstructionState<T> {

    public UpdateInstructionState() {
        super(InstructionStatus.UPDATING);
    }

    @Override
    public void updateConfirm(InstructionAggregate<T> aggregate, UpdateConfirmIstrCmd<T> cmd) {
        IstrUpdateConfirmedEvt<T> evt = new IstrUpdateConfirmedEvt<>();
        evt.copyOf(cmd);
        evt.setTradeElement(cmd.getTradeElement());
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void updateFail(InstructionAggregate<T> instructionAggregate, UpdateFailIstrCmd cmd) {
        IstrFailedEvt evt = new IstrFailedEvt();
        evt.copyOf(cmd);
        evt.setRiskInfos(cmd.getRiskRiskInfos());
        AggregateLifecycle.apply(evt);
    }
}
