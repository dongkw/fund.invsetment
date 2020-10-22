package fund.investment.instruction.domain.model.aggregate;

import com.google.gson.Gson;
import fund.investment.infrastructure.common.DomainAggregate;
import fund.investment.infrastructure.instruction.domain.model.command.*;
import fund.investment.infrastructure.instruction.domain.model.enumeration.ApprovalStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.DistributeStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.event.*;
import fund.investment.instruction.domain.model.aggregate.status.*;
import fund.investment.instruction.domain.model.entity.IstrTradeElement;
import fund.investment.instruction.domain.model.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateMember;

import java.util.Objects;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class InstructionAggregate extends DomainAggregate {

    private InstructionState instructionState;

    private String errorCode;

    private String errorMsg;

    private String unitId;

    private String accountId;

    private String userId;

    private TradeType tradeType;

    @AggregateMember
    private IstrTradeElement istrTradeElement;

    private ApprovalStatus approvalStatus;

    private DistributeStatus distributeStatus;

    @AggregateMember
    private OrderDetail orderDetail;

    @CommandHandler
    public void handle(CreateConfirmIstrCmd cmd) {
        log.info("Receive command: {}", cmd);
        getInstructionState().createConfirm(cmd);
    }

    @CommandHandler
    public void handle(CreateFailIstrCmd cmd) {
        log.info("Receive command: {}", cmd);
        getInstructionState().createFail(cmd);
    }

    @CommandHandler
    public void handle(CancelConfIstrCmd cmd) {
        log.info("Receive command: {}", cmd);
        getInstructionState().cancelConfirm(cmd);
    }

    @CommandHandler
    public void handle(AprvPassIstrCmd cmd) {
        log.info("Receive command: {}", cmd);
        getInstructionState().aprvPass(cmd);
    }

    @CommandHandler
    public void handle(DistributeIstrCmd cmd) {
        log.info("Receive command: {}", cmd);
        getInstructionState().distribute(cmd);
    }

    @CommandHandler
    public void handle(ReceiveIstrFillCmd cmd) {
        log.info("Receive command: {}", cmd);
        getInstructionState().receiveFill(this, cmd);
    }

    @EventSourcingHandler
    public void handle(IstrCancellingEvt evt) {
        this.instructionState = new CancellingInstructionState();
        log.info(String.format("[instruction] Cancel Instruction event: %s", new Gson().toJson(evt)));
    }

    @EventSourcingHandler
    public void handle(IstrConfirmedEvt evt) {
        log.info("Recieved Event: {}", evt);
        this.instructionState = new ConfirmedInstructionState();
    }

    @EventSourcingHandler
    public void handle(IstrPassedEvt evt) {
        log.info("Recieved Event: {}", evt);
        this.instructionState = new PassedInstructionState();
    }

    @EventSourcingHandler
    public void handle(IstrPendingEvt evt) {
        log.info("Recieved Event: {}", evt);
        this.instructionState = new PendingInstructionState();
    }

    @EventSourcingHandler
    public void handle(IstrExecutingEvt evt) {
        log.info("Recieved Event: {}", evt);
    }

    @EventSourcingHandler
    public void handle(IstrFailedEvt evt) {
        log.info("Recieved Event: {}", evt);
        this.instructionState = new FailedInstructionState();
    }

    @EventSourcingHandler
    public void handle(IstrCompletedEvt evt) {
        log.info("Recieved Event: {}", evt);
        this.instructionState = new CompletedInstructionState();
    }

    @EventSourcingHandler
    public void handle(IstrCancelledEvt evt) {
        log.info("Recieved Event: {}", evt);
        this.instructionState = new CancelledInstructionState();
    }

    @EventHandler
    public void handle(IstrOrderCreatedEvt evt) {
        log.info("Recieved Event: {}", evt);
        //TODO 添加委托赋值操作
        this.orderDetail = new OrderDetail();
        this.instructionState = new ExcutingInstructionState();
    }

    @EventSourcingHandler
    public void handle(IstrOrderCancelledEvt evt) {
        log.info("Recieved Event: {}", evt);
        if (Objects.isNull(this.orderDetail)) {
            this.orderDetail = new OrderDetail();
        }
        boolean flag = this.orderDetail.cancelOrder(evt);
        if (flag) {
            this.instructionState = new PendingInstructionState();
            log.info("Order cancelled all, state change to pending: {}", evt);
        }
    }

    @EventSourcingHandler
    public void handle(IstrFillReceivedEvt evt) {
        log.info("Recieved Event: {}", evt);
        this.instructionState = new ExcutingInstructionState();
    }
}
