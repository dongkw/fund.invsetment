package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.*;
import fund.investment.basic.instruction.api.entity.OrderDetail;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.*;
import fund.investment.basic.instruction.api.valueobject.TradeElement;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.Objects;

@Slf4j
public class CancellingInstructionState<T extends TradeElement> extends InstructionState<T> {

    public CancellingInstructionState() {
        super(InstructionStatus.CANCELLING);
    }

    @Override
    public void cancelConfirm(InstructionAggregate<T> instructionAggregate, CancelConfIstrCmd cmd) {
        IstrCancelledEvt istrCancelledEvt = new IstrCancelledEvt();
        istrCancelledEvt.copyOf(cmd);
        istrCancelledEvt.setCancelType(cmd.getCancelType());
        istrCancelledEvt.setCancelMsg(cmd.getCancelMsg());
        AggregateLifecycle.apply(istrCancelledEvt);
    }

    @Override
    public void cancelFail(InstructionAggregate<T> instructionAggregate, RollbackCancelIstrCmd cmd) {
        IstrFailedEvt evt = new IstrFailedEvt();
        evt.copyOf(cmd);
        evt.setRiskInfos(cmd.getRiskInfos());
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void createOrder(InstructionAggregate<T> instructionAggregate, CreateIstrOrderCmd esCreateIstrOrderCmd) {
        IstrOrderFailedEvt istrOrderFailedEvt = new IstrOrderFailedEvt();
        istrOrderFailedEvt.copyOf(esCreateIstrOrderCmd);
        istrOrderFailedEvt.setOrderId(esCreateIstrOrderCmd.getOrderId());
        istrOrderFailedEvt.setFailMsg("指令撤销中，创建委托失败");
        AggregateLifecycle.apply(istrOrderFailedEvt);
    }

    @Override
    public void receiveFill(InstructionAggregate<T> instructionAggregate, ReceiveIstrFillCmd cmd) {
        OrderDetail orderDetail = instructionAggregate.getOrderDetail();
        if (Objects.isNull(orderDetail)) {
            orderDetail = new OrderDetail();
        }
        IstrFillReceivedEvt istrFillReceivedEvt = new IstrFillReceivedEvt();
        istrFillReceivedEvt.setRequestId(cmd.getRequestId());
        istrFillReceivedEvt.setFillId(cmd.getFillId());
        istrFillReceivedEvt.setFillQuantity(cmd.getFillQuantity());
        istrFillReceivedEvt.setOrderId(cmd.getOrderId());
        istrFillReceivedEvt.setId(cmd.getId());
        istrFillReceivedEvt.setTradeType(cmd.getTradeType());
        orderDetail.receiveFill(istrFillReceivedEvt);
        //todo 字段逻辑
        if (instructionAggregate.getTradeElement().getFtInstrAmt().doubleValue()
                - orderDetail.getFillQuantity() == 0) {
            IstrCompletedEvt istrCompletedEvt = new IstrCompletedEvt();
            istrCompletedEvt.setRequestId(cmd.getRequestId());
            istrCompletedEvt.setId(cmd.getId());
            istrCompletedEvt.setTradeType(cmd.getTradeType());
            AggregateLifecycle.apply(istrCompletedEvt);
        } else {
            AggregateLifecycle.apply(istrFillReceivedEvt);
        }
    }
}
