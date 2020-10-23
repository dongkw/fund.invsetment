package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.command.ReceiveIstrFillCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.event.*;
import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import fund.investment.instruction.domain.model.entity.IstrTradeElement;
import fund.investment.instruction.domain.model.entity.OrderDetail;
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
        log.info("Receive command: {}", cmd);
        IstrTradeElement istrTradeElement = instructionAggregate.getIstrTradeElement();
        if (!cmd.getTradeType().name().equals(TradeType.UNDEFINED.name())) {
            IstrOrderCreatedEvt istrOrderCreatedEvt = new IstrOrderCreatedEvt();
            istrOrderCreatedEvt.setOrderId(cmd.getOrderId());
            istrOrderCreatedEvt.setId(cmd.getId());
//            istrOrderCreatedEvt.setOrderQuantity(cmd.getOrderTradeElement().getQuantity());
            AggregateLifecycle.apply(istrOrderCreatedEvt);
            log.info("Dispached Event: {}", istrOrderCreatedEvt);
        } else {
            IstrOrderFailedEvt istrOrderFailedEvt = new IstrOrderFailedEvt();
            istrOrderFailedEvt.setId(cmd.getId());
            istrOrderFailedEvt.setOrderId(cmd.getOrderId());
            istrOrderFailedEvt.setFailMsg("交易参数不正确，创建委托失败");
            AggregateLifecycle.apply(istrOrderFailedEvt);
            log.info("Dispached Event: {}", istrOrderFailedEvt);
        }
    }

    @Override
    public void cancelOrder(CancelIstrOrderCmd cancelIstrOrderCmd) {
        log.info("Receive command: {}", cancelIstrOrderCmd);
        IstrOrderCancelledEvt istrOrderCancelledEvt = new IstrOrderCancelledEvt();
        istrOrderCancelledEvt.setId(cancelIstrOrderCmd.getId());
        istrOrderCancelledEvt.setOrderId(cancelIstrOrderCmd.getOrderId());
        istrOrderCancelledEvt.setCancelQuantity(cancelIstrOrderCmd.getCancelQuantity());
        AggregateLifecycle.apply(istrOrderCancelledEvt);
        log.info("Dispached Event: {}", istrOrderCancelledEvt);
    }

    @Override
    public void receiveFill(InstructionAggregate instructionAggregate, ReceiveIstrFillCmd cmd) {
        log.info("Receive command: {}", cmd);
        OrderDetail orderDetail = instructionAggregate.getOrderDetail();
        if (Objects.isNull(orderDetail)) {
            orderDetail = new OrderDetail();
        }
        IstrFillReceivedEvt istrFillReceivedEvt = new IstrFillReceivedEvt();
        istrFillReceivedEvt.setFillId(cmd.getFillId());
        istrFillReceivedEvt.setFillQuantity(cmd.getFillQuantity());
        istrFillReceivedEvt.setOrderId(cmd.getOrderId());
        istrFillReceivedEvt.setId(cmd.getId());
        istrFillReceivedEvt.setTradeType(cmd.getTradeType());
        orderDetail.receiveFill(istrFillReceivedEvt);
        instructionAggregate.setOrderDetail(orderDetail);
        IstrTradeElement istrTradeElement = instructionAggregate.getIstrTradeElement();
        if (istrTradeElement.getQuantity() - orderDetail.getFillQuantity() == 0) {
            IstrCompletedEvt istrCompletedEvt = new IstrCompletedEvt();
            istrCompletedEvt.setId(cmd.getId());
            istrCompletedEvt.setTradeType(cmd.getTradeType());
            AggregateLifecycle.apply(istrCompletedEvt);
            log.info("Dispached Event: {}", istrCompletedEvt);
        } else {
            AggregateLifecycle.apply(istrFillReceivedEvt);
            log.info("Dispached Event: {}", istrFillReceivedEvt);
        }
    }
}
