package fund.investment.basic.instruction.server.aggregate.status;

import fund.investment.basic.instruction.api.command.CreateIstrOrderCmd;
import fund.investment.basic.instruction.api.command.ReceiveIstrFillCmd;
import fund.investment.basic.instruction.api.entity.IstrTradeElement;
import fund.investment.basic.instruction.api.enumeration.InstructionStatus;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.event.IstrCompletedEvt;
import fund.investment.basic.instruction.api.event.IstrOrderCreatedEvt;
import fund.investment.basic.instruction.api.event.IstrOrderFailedEvt;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PendingInstructionState extends CancelableInstructionState {

    public PendingInstructionState() {
        super(InstructionStatus.PENDING);
    }

    @Override
    public void createOrder(InstructionAggregate instructionAggregate, CreateIstrOrderCmd cmd) {
        IstrTradeElement istrTradeElement = instructionAggregate.getIstrTradeElement();
        if (!cmd.getTradeType().name().equals(TradeType.UNDEFINED.name())) {
            IstrOrderCreatedEvt istrOrderCreatedEvt = new IstrOrderCreatedEvt();
            istrOrderCreatedEvt.setOrderId(cmd.getOrderId());
            istrOrderCreatedEvt.setId(cmd.getId());
            istrOrderCreatedEvt.setRequestId(cmd.getRequestId());
            istrOrderCreatedEvt.setSkInstr(instructionAggregate.getSkId());
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
    public void receiveFill(InstructionAggregate instructionAggregate, ReceiveIstrFillCmd cmd) {
//        OrderDetail orderDetail = instructionAggregate.getOrderDetail();
//        if (Objects.isNull(orderDetail)) {
//            orderDetail = new OrderDetail(OrderStatus.UNDEFINED,0,0,0,null);
//        }
//        IstrFillReceivedEvt istrFillReceivedEvt = new IstrFillReceivedEvt();
//        istrFillReceivedEvt.setFillId(cmd.getFillId());
//        istrFillReceivedEvt.setFillQuantity(cmd.getFillQuantity());
//        istrFillReceivedEvt.setOrderId(cmd.getOrderId());
//        istrFillReceivedEvt.setId(cmd.getId());
//        istrFillReceivedEvt.setRequestId(cmd.getRequestId());
//        istrFillReceivedEvt.setTradeType(cmd.getTradeType());
//        orderDetail.receiveFill(istrFillReceivedEvt);
//        instructionAggregate.setOrderDetail(orderDetail);
//        IstrTradeElement istrTradeElement = instructionAggregate.getIstrTradeElement();
//        if (true) {
        IstrCompletedEvt istrCompletedEvt = new IstrCompletedEvt();
        istrCompletedEvt.setId(cmd.getId());
        istrCompletedEvt.setSkId(cmd.getSkId());
        istrCompletedEvt.setSkInstr(cmd.getSkInstr());
        istrCompletedEvt.setRequestId(cmd.getRequestId());
        istrCompletedEvt.setTradeType(cmd.getTradeType());
        istrCompletedEvt.setChInsDealStatus(cmd.getChInsDealStatus());

        istrCompletedEvt.setChLastModifiedId(cmd.getChLastModifiedId());
        istrCompletedEvt.setTsLastModifiedTime(cmd.getTsLastModifiedTime());
        AggregateLifecycle.apply(istrCompletedEvt);
//        } else {
//            AggregateLifecycle.apply(istrFillReceivedEvt);
//        }
    }

}
