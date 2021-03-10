package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.command.CancelIstrOrderCmd;
import fund.investment.basic.instruction.api.command.CreateIstrOrderCmd;
import fund.investment.basic.instruction.api.command.ReceiveIstrFillCmd;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.event.*;
import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.InstructionState;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class ExcutingInstructionState<T extends InstructionElement> extends InstructionState<T> {

    public ExcutingInstructionState() {
        super(InstructionStatus.EXECUTING);
    }

    @Override
    public void handle(InstructionAggregate instructionAggregate, CreateIstrOrderCmd cmd) {
        if (!cmd.getTradeType().name().equals(TradeType.UNDEFINED.name())) {
            IstrOrderCreatedEvt istrOrderCreatedEvt = new IstrOrderCreatedEvt();
            istrOrderCreatedEvt.setOrderId(cmd.getOrderId());
            istrOrderCreatedEvt.setId(cmd.getId());
            istrOrderCreatedEvt.setRequestId(cmd.getRequestId());
            //            istrOrderCreatedEvt.setOrderQuantity(cmd.getOrderTradeElement().getQuantity());
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
    public void handle(CancelIstrOrderCmd cancelIstrOrderCmd) {
        IstrOrderCancelledEvt istrOrderCancelledEvt = new IstrOrderCancelledEvt();
        istrOrderCancelledEvt.setId(cancelIstrOrderCmd.getId());
        istrOrderCancelledEvt.setOrderId(cancelIstrOrderCmd.getOrderId());
        istrOrderCancelledEvt.setCancelQuantity(cancelIstrOrderCmd.getCancelQuantity());
        istrOrderCancelledEvt.setRequestId(cancelIstrOrderCmd.getRequestId());
        AggregateLifecycle.apply(istrOrderCancelledEvt);
    }

    @Override
    public void handle(InstructionAggregate<T> instructionAggregate, ReceiveIstrFillCmd cmd) {
        IstrCompletedEvt istrCompletedEvt = new IstrCompletedEvt();
        istrCompletedEvt.copyOf(cmd);
        AggregateLifecycle.apply(istrCompletedEvt);

    }

    @Override
    public void handle(InstructionAggregate<T> aggregate, CancelIstrCmd cancelIstrCmd) {
        IstrCancellingEvt istrCancellingEvt = new IstrCancellingEvt();
        istrCancellingEvt.copyOf(cancelIstrCmd);
        istrCancellingEvt.setRiskInfos(cancelIstrCmd.getRiskInfos());
        AggregateLifecycle.apply(istrCancellingEvt);
    }
}
