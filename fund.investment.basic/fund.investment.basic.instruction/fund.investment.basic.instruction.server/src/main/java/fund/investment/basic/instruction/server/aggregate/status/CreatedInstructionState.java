package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CreateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.CreateFailIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrConfirmedEvt;
import fund.investment.basic.instruction.api.event.IstrFailedEvt;
import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.InstructionState;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CreatedInstructionState<T extends InstructionElement> extends InstructionState<T> {

    public CreatedInstructionState() {
        super(InstructionStatus.CREATED);
    }

    @Override
    public void handle(InstructionAggregate<T> aggregate, CreateConfirmIstrCmd<T> cmd) {
        IstrConfirmedEvt<T> evt = new IstrConfirmedEvt<>();
        evt.copyOf(cmd);
        evt.setTradeElement(cmd.getTradeElement());
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void handle(InstructionAggregate<T> instructionAggregate, CreateFailIstrCmd cmd) {
        IstrFailedEvt evt = new IstrFailedEvt();
        evt.copyOf(cmd);
        evt.setRiskInfos(cmd.getRiskRiskInfos());
        AggregateLifecycle.apply(evt);
    }
}
