package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CancelConfIstrCmd;
import fund.investment.basic.instruction.api.command.CancelIstrOrderCmd;
import fund.investment.basic.instruction.api.command.CreateIstrOrderCmd;
import fund.investment.basic.instruction.api.command.ReceiveIstrFillCmd;
import fund.investment.basic.instruction.api.entity.IstrTradeElement;
import fund.investment.basic.instruction.api.entity.OrderDetail;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.event.IstrCancelledEvt;
import fund.investment.basic.instruction.api.event.IstrCompletedEvt;
import fund.investment.basic.instruction.api.event.IstrFillReceivedEvt;
import fund.investment.basic.instruction.api.event.IstrOrderFailedEvt;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.Objects;

@Slf4j
public class CancellingInstructionState extends InstructionState {

    public CancellingInstructionState() {
        super(InstructionStatus.CANCELLED);
    }

    @Override
    public void cancelConfirm(InstructionAggregate instructionAggregate, CancelConfIstrCmd cmd) {
        IstrCancelledEvt istrCancelledEvt = new IstrCancelledEvt();
        istrCancelledEvt.setId(cmd.getId());
        istrCancelledEvt.setRequestId(cmd.getRequestId());
        istrCancelledEvt.setTradeType(cmd.getTradeType());
        istrCancelledEvt.setSkId(instructionAggregate.getSkId());
        istrCancelledEvt.setSkInstr(instructionAggregate.getSkInstr());
        istrCancelledEvt.setCancelType(cmd.getCancelType());
        istrCancelledEvt.setCancelMsg(cmd.getCancelMsg());
        istrCancelledEvt.setChLastModifiedId(cmd.getChLastModifiedId());
        istrCancelledEvt.setTsLastModifiedTime(cmd.getTsLastModifiedTime());
        istrCancelledEvt.setChInstrSource(cmd.getChInstrSource());
        istrCancelledEvt.setChSourceKey(cmd.getChSourceKey());
        istrCancelledEvt.setChSourceNo(cmd.getChSourceNo());
        AggregateLifecycle.apply(istrCancelledEvt);
    }

    @Override
    public void createOrder(InstructionAggregate instructionAggregate, CreateIstrOrderCmd esCreateIstrOrderCmd) {
        IstrOrderFailedEvt istrOrderFailedEvt = new IstrOrderFailedEvt(
                esCreateIstrOrderCmd.getId(),
                null,
                esCreateIstrOrderCmd.getTradeType(),
                null,
                null,
                null,
                null,
                null,
                esCreateIstrOrderCmd.getOrderId(),
                "指令撤销中，创建委托失败");
        istrOrderFailedEvt.setRequestId(esCreateIstrOrderCmd.getRequestId());
        AggregateLifecycle.apply(istrOrderFailedEvt);
    }

    @Override
    public void cancelOrder(CancelIstrOrderCmd cancelIstrOrderCmd) {
    }

    @Override
    public void receiveFill(InstructionAggregate instructionAggregate, ReceiveIstrFillCmd cmd) {
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
        IstrTradeElement istrTradeElement = instructionAggregate.getIstrTradeElement();
        if (istrTradeElement.getQuantity() - orderDetail.getFillQuantity() == 0) {
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
