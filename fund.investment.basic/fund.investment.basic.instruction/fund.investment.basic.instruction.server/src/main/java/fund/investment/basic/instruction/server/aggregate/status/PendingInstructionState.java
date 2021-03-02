package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.command.CreateIstrOrderCmd;
import fund.investment.basic.instruction.api.command.ReceiveIstrFillCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.event.IstrCancellingEvt;
import fund.investment.basic.instruction.api.event.IstrCompletedEvt;
import fund.investment.basic.instruction.api.event.IstrOrderCreatedEvt;
import fund.investment.basic.instruction.api.event.IstrOrderFailedEvt;
import fund.investment.basic.instruction.api.valueobject.TradeElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PendingInstructionState<T extends TradeElement> extends InstructionState<T> {

    public PendingInstructionState() {
        super(InstructionStatus.PENDING);
    }

    @Override
    public void createOrder(InstructionAggregate<T> instructionAggregate, CreateIstrOrderCmd cmd) {
        if (!cmd.getTradeType().name().equals(TradeType.UNDEFINED.name())) {
            IstrOrderCreatedEvt istrOrderCreatedEvt = new IstrOrderCreatedEvt();
            istrOrderCreatedEvt.setOrderId(cmd.getOrderId());
            istrOrderCreatedEvt.setId(cmd.getId());
            istrOrderCreatedEvt.setRequestId(cmd.getRequestId());
            AggregateLifecycle.apply(istrOrderCreatedEvt);
        } else {
            IstrOrderFailedEvt istrOrderFailedEvt = new IstrOrderFailedEvt();
            istrOrderFailedEvt.setId(cmd.getId());
            istrOrderFailedEvt.setRequestId(cmd.getRequestId());
            istrOrderFailedEvt.setOrderId(cmd.getOrderId());
            istrOrderFailedEvt.setFailMsg("交易参数不正确，创建委托失败");
            AggregateLifecycle.apply(istrOrderFailedEvt);
        }
    }


    @Override
    public void receiveFill(InstructionAggregate instructionAggregate, ReceiveIstrFillCmd cmd) {
        IstrCompletedEvt istrCompletedEvt = new IstrCompletedEvt();
        istrCompletedEvt.copyOf(cmd);
        AggregateLifecycle.apply(istrCompletedEvt);

    }

    @Override
    public void cancel(InstructionAggregate<T> aggregate, CancelIstrCmd cancelIstrCmd) {
        IstrCancellingEvt istrCancellingEvt = new IstrCancellingEvt();
        istrCancellingEvt.copyOf(cancelIstrCmd);
        istrCancellingEvt.setRiskInfos(cancelIstrCmd.getRiskInfos());
        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
