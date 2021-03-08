package fund.investment.basic.instruction.server.aggregate;

import fund.investment.basic.common.DomainAggregate;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.api.command.*;
import fund.investment.basic.instruction.api.entity.OrderDetail;
import fund.investment.basic.instruction.api.event.*;
import fund.investment.basic.instruction.api.valueobject.InstructionElement;
import fund.investment.basic.instruction.server.aggregate.status.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;

import java.util.Objects;

@Data
@Slf4j
@NoArgsConstructor
public class InstructionAggregate<T extends InstructionElement> extends DomainAggregate {

    protected InstructionState<T> instructionState;
    protected InstructionState<T> lastState;

    @AggregateMember
    protected OrderDetail orderDetail;

    @AggregateMember
    private T tradeElement;

    /*
     * 创建指令
     */
    @CommandHandler
    public InstructionAggregate(CreateIstrCmd<T> cmd) {
        IstrCreatedEvt<T> evt = new IstrCreatedEvt<>();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void handle(CreateConfirmIstrCmd<T> cmd) {
        getInstructionState().createConfirm(this, cmd);
    }

    @CommandHandler
    public void handle(CreateFailIstrCmd cmd) {
        getInstructionState().createFail(this, cmd);
    }

    @EventHandler
    public void on(IstrCreatedEvt<T> evt) {
        log.info("create {}", evt);
        BeanUtils.copyProperties(evt, this);
        this.instructionState = new CreatedInstructionState<>();
    }

    @EventSourcingHandler
    public void handle(IstrConfirmedEvt<T> evt) {
        log.info("confirm {}", evt);
        this.instructionState = new ConfirmedInstructionState<>();
    }


    /*
     * 修改指令
     */
    @CommandHandler
    public void handle(UpdateIstrCmd<T> cmd) {
        getInstructionState().update(this, cmd);
    }

    @CommandHandler
    public void handle(UpdateConfirmIstrCmd<T> cmd) {
        getInstructionState().updateConfirm(this, cmd);
    }

    @CommandHandler
    public void handler(UpdateFailIstrCmd cmd) {
        getInstructionState().updateFail(this, cmd);
    }


    @EventSourcingHandler
    public void on(IstrUpdatedEvt<T> evt) {
        log.info("update {}", evt);
        this.lastState = getInstructionState();
        this.instructionState = new UpdateInstructionState<>();
    }

    @EventSourcingHandler
    public void on(IstrUpdateConfirmedEvt<T> evt) {
        log.info("update confirm {}", evt);
        this.instructionState = lastState;
        lastState = null;
        BeanUtils.copyPropertiesIgnoreNull(evt.getTradeElement(), this.tradeElement);
    }

    /*
     * 取消
     */

    @CommandHandler
    public void handle(CancelIstrCmd cmd) {
        getInstructionState().cancel(this, cmd);
    }

    @CommandHandler
    public void handle(CancelConfIstrCmd cmd) {
        getInstructionState().cancelConfirm(this, cmd);
    }

    @CommandHandler
    public void handler(RollbackCancelIstrCmd cmd) {
        getInstructionState().cancelFail(this, cmd);
    }

    @EventSourcingHandler
    public void on(IstrCancellingEvt evt) {
        log.info("cancelling {}", evt);
        this.instructionState = new CancellingInstructionState<>();
    }

    @EventSourcingHandler
    public void handle(IstrCancelledEvt evt) {
        log.info("cancelled {}", evt);
        this.instructionState = new CancelledInstructionState<>();
    }


    @CommandHandler
    public void handle(ApproveIstrCmd cmd) {
        getInstructionState().aprvPass(this, cmd);
    }

    @CommandHandler
    public void handle(DistributeIstrCmd cmd) {
        getInstructionState().distribute(this, cmd);
    }

    @CommandHandler
    public void handle(ReceiveIstrFillCmd cmd) {
        getInstructionState().receiveFill(this, cmd);
    }

    @EventSourcingHandler
    public void handle(IstrPassedEvt evt) {
        this.instructionState = new PassedInstructionState<>();
    }

    @EventSourcingHandler
    public void handle(IstrPendingEvt evt) {
        this.instructionState = new PendingInstructionState<>();
    }

    @EventSourcingHandler
    public void handle(IstrCompletedEvt evt) {
        this.instructionState = new CompletedInstructionState<>();
    }

    @EventSourcingHandler
    public void handle(IstrFailedEvt evt) {
        log.info("fail {}", evt);
        this.instructionState = new FailedInstructionState<>();
    }

    @EventSourcingHandler
    public void handle(IstrOrderCreatedEvt evt) {
        //TODO 添加委托赋值操作
        this.orderDetail = new OrderDetail();
        this.instructionState = new ExcutingInstructionState<>();
    }

    @EventSourcingHandler
    public void handle(IstrOrderCancelledEvt evt) {
        if (Objects.isNull(this.orderDetail)) {
            this.orderDetail = new OrderDetail();
        }
        boolean flag = this.orderDetail.cancelOrder(evt);
        if (flag) {
            this.instructionState = new PendingInstructionState<>();
            log.info("Order cancelled all, state change to pending: {}", evt);
        }
    }


}
