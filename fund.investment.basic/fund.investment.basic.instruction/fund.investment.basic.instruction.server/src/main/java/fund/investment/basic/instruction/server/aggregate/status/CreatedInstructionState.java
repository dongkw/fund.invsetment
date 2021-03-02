package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CreateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.CreateFailIstrCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrConfirmedEvt;
import fund.investment.basic.instruction.api.event.IstrFailedEvt;
import fund.investment.basic.instruction.api.valueobject.TradeElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CreatedInstructionState<T extends TradeElement> extends InstructionState<T> {

    public CreatedInstructionState() {
        super(InstructionStatus.CREATED);
    }

    @Override
    public void createConfirm(InstructionAggregate<T> aggregate, CreateConfirmIstrCmd<T> cmd) {
        IstrConfirmedEvt<T> evt = new IstrConfirmedEvt<>();
        evt.copyOf(cmd);
        evt.setTradeElement(cmd.getTradeElement());
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void createFail(InstructionAggregate<T> instructionAggregate, CreateFailIstrCmd cmd) {
        IstrFailedEvt evt = new IstrFailedEvt();
        evt.copyOf(cmd);
        evt.setRiskInfos(cmd.getRiskRiskInfos());
        AggregateLifecycle.apply(evt);
    }
}
