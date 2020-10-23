package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.command.CancelConfIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.command.ReceiveIstrFillCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCompletedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrFillReceivedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderFailedEvt;
import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import fund.investment.instruction.domain.model.entity.IstrTradeElement;
import fund.investment.instruction.domain.model.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.Objects;

@Slf4j
public class CancellingInstructionState extends InstructionState {

    public CancellingInstructionState() {
        super(InstructionStatus.CANCELLED);
    }

    @Override
    public void cancelConfirm(CancelConfIstrCmd cmd) {
        log.info("Receive command: {}", cmd);
        IstrCancelledEvt istrCancelledEvt = new IstrCancelledEvt();
        istrCancelledEvt.setId(cmd.getId());
        istrCancelledEvt.setTradeType(cmd.getTradeType());
        AggregateLifecycle.apply(istrCancelledEvt);
        log.info("Dispached Event: {}", istrCancelledEvt);
    }

    @Override
    public void createOrder(InstructionAggregate instructionAggregate, CreateIstrOrderCmd esCreateIstrOrderCmd) {
        log.info("Receive command: {}", esCreateIstrOrderCmd);
        IstrOrderFailedEvt istrOrderFailedEvt = new IstrOrderFailedEvt(
                esCreateIstrOrderCmd.getTradeType(),
                esCreateIstrOrderCmd.getId(),
                esCreateIstrOrderCmd.getOrderId(),
                "指令撤销中，创建委托失败");
        AggregateLifecycle.apply(istrOrderFailedEvt);
        log.info("Dispached Event: {}", istrOrderFailedEvt);
    }

    @Override
    public void cancelOrder(CancelIstrOrderCmd cancelIstrOrderCmd) {
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
