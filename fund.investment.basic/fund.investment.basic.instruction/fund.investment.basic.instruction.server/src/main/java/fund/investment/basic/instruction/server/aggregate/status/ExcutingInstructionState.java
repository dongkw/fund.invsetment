package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CancelIstrOrderCmd;
import fund.investment.basic.instruction.api.command.CreateIstrOrderCmd;
import fund.investment.basic.instruction.api.command.ReceiveIstrFillCmd;
import fund.investment.basic.instruction.api.entity.IstrTradeElement;
import fund.investment.basic.instruction.api.entity.OrderDetail;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.enumeration.OrderStatus;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.event.*;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.Objects;

@Slf4j
public class ExcutingInstructionState extends CancelableInstructionState {

    public ExcutingInstructionState() {
        super(InstructionStatus.EXECUTING);
    }

    @Override
    public void createOrder(InstructionAggregate instructionAggregate, CreateIstrOrderCmd cmd) {
        IstrTradeElement istrTradeElement = instructionAggregate.getIstrTradeElement();
        if (!cmd.getTradeType().name().equals(TradeType.UNDEFINED.name())) {
            IstrOrderCreatedEvt istrOrderCreatedEvt = new IstrOrderCreatedEvt();
            istrOrderCreatedEvt.setOrderId(cmd.getOrderId());
            istrOrderCreatedEvt.setId(cmd.getId());
            istrOrderCreatedEvt.setRequestId(cmd.getRequestId());
            istrOrderCreatedEvt.setSkId(instructionAggregate.getSkId());
            istrOrderCreatedEvt.setSkInstr(instructionAggregate.getSkInstr());
//            istrOrderCreatedEvt.setOrderQuantity(cmd.getOrderTradeElement().getQuantity());
            AggregateLifecycle.apply(istrOrderCreatedEvt);
        } else {
            IstrOrderFailedEvt istrOrderFailedEvt = new IstrOrderFailedEvt();
            istrOrderFailedEvt.setId(cmd.getId());
            istrOrderFailedEvt.setRequestId(cmd.getRequestId());
            istrOrderFailedEvt.setOrderId(cmd.getOrderId());
            istrOrderFailedEvt.setFailMsg("交易参数不正确，创建委托失败");
            istrOrderFailedEvt.setSkId(instructionAggregate.getSkId());
            istrOrderFailedEvt.setSkInstr(instructionAggregate.getSkInstr());
            AggregateLifecycle.apply(istrOrderFailedEvt);
        }
    }

    @Override
    public void cancelOrder(CancelIstrOrderCmd cancelIstrOrderCmd) {
        IstrOrderCancelledEvt istrOrderCancelledEvt = new IstrOrderCancelledEvt();
        istrOrderCancelledEvt.setId(cancelIstrOrderCmd.getId());
        istrOrderCancelledEvt.setOrderId(cancelIstrOrderCmd.getOrderId());
        istrOrderCancelledEvt.setCancelQuantity(cancelIstrOrderCmd.getCancelQuantity());
        istrOrderCancelledEvt.setRequestId(cancelIstrOrderCmd.getRequestId());
        AggregateLifecycle.apply(istrOrderCancelledEvt);
    }

    @Override
    public void receiveFill(InstructionAggregate instructionAggregate, ReceiveIstrFillCmd cmd) {
        OrderDetail orderDetail = instructionAggregate.getOrderDetail();
        if (Objects.isNull(orderDetail)) {
            orderDetail = new OrderDetail(OrderStatus.UNDEFINED,0,0,0,null);
        }
        IstrFillReceivedEvt istrFillReceivedEvt = new IstrFillReceivedEvt();
        istrFillReceivedEvt.setFillId(cmd.getFillId());
        istrFillReceivedEvt.setFillQuantity(cmd.getFillQuantity());
        istrFillReceivedEvt.setOrderId(cmd.getOrderId());
        istrFillReceivedEvt.setId(cmd.getId());
        istrFillReceivedEvt.setRequestId(cmd.getRequestId());
        istrFillReceivedEvt.setTradeType(cmd.getTradeType());
        orderDetail.receiveFill(istrFillReceivedEvt);
        instructionAggregate.setOrderDetail(orderDetail);
//        IstrTradeElement istrTradeElement = instructionAggregate.getIstrTradeElement();
        if (true) {
            IstrCompletedEvt istrCompletedEvt = new IstrCompletedEvt();
            istrCompletedEvt.setId(cmd.getId());
            istrCompletedEvt.setRequestId(cmd.getRequestId());
            istrCompletedEvt.setTradeType(cmd.getTradeType());
            AggregateLifecycle.apply(istrCompletedEvt);
        } else {
            AggregateLifecycle.apply(istrFillReceivedEvt);
        }
    }
}
