package fund.investment.app.pledge.repo.server.aggregate.instruction;

import fund.investment.app.pledge.repo.api.command.instruction.*;
import fund.investment.app.pledge.repo.api.event.instruction.PRIstrCreatedEvt;
import fund.investment.app.pledge.repo.api.event.instruction.PRIstrUpdateConfirmedEvt;
import fund.investment.app.pledge.repo.api.event.instruction.PRIstrUpdatedEvt;
import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeInstructionElement;
import fund.investment.app.pledge.repo.server.aggregate.instruction.status.PrConfirmState;
import fund.investment.app.pledge.repo.server.aggregate.instruction.status.PrPassedState;
import fund.investment.app.pledge.repo.server.aggregate.instruction.status.PrPendingState;
import fund.investment.app.pledge.repo.server.aggregate.instruction.status.PrUpdateState;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.api.entity.OrderDetail;
import fund.investment.basic.instruction.api.event.*;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.status.CreatedInstructionState;
import fund.investment.basic.instruction.server.aggregate.status.ExcutingInstructionState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Data
@Slf4j
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger")
@NoArgsConstructor
public class PledgeRepoIstrAggr<T> extends InstructionAggregate<PledgeInstructionElement> {

    /**
     * 创建质押式回购
     *
     * @param cmd
     */
    @CommandHandler
    public PledgeRepoIstrAggr(PRCreateIstrCmd cmd) {
        PRIstrCreatedEvt<T> prIstrCreatedEvt = new PRIstrCreatedEvt<>();
        BeanUtils.copyProperties(cmd, prIstrCreatedEvt);
        AggregateLifecycle.apply(prIstrCreatedEvt);
    }
    @EventSourcingHandler
    public void on(PRIstrCreatedEvt<T> evt) {
        log.info("create {}", evt);
        BeanUtils.copyProperties(evt, this);
        this.instructionState = new CreatedInstructionState<>();
    }
    @EventSourcingHandler
    public void handle(IstrConfirmedEvt<PledgeInstructionElement> evt) {
        log.info("confirm {}", evt);
        this.instructionState = new PrConfirmState<>();
    }

    /**
     * 修改质押式回购
     *
     * @param cmd
     */
    @CommandHandler
    public void handle(PRUpdateIstrCmd cmd) {
        getInstructionState().handle(this, cmd);
    }

    @CommandHandler
    public void handle(PRUpdateConfirmIstrCmd cmd) {
        getInstructionState().handle(this, cmd);
    }

    @CommandHandler
    public void handle(PRUpdateFailIstrCmd cmd) {
        getInstructionState().handle(this, cmd);
    }

    @EventSourcingHandler
    public void on(PRIstrUpdatedEvt evt) {
        log.info("update {}", evt);
        this.lastState = getInstructionState();
        this.instructionState = new PrUpdateState<>();
    }

    @EventSourcingHandler
    public void on(PRIstrUpdateConfirmedEvt evt) {
        log.info("update confirm {}", evt);
        this.setInstructionState(getLastState());
        this.setLastState(null);
        BeanUtils.copyPropertiesIgnoreNull(evt.getTradeElement(), this.getTradeElement());
    }


    @CommandHandler
    public void handle(PRCancelIstrCmd cmd) {
        getInstructionState().handle(this, cmd);
    }


    @EventSourcingHandler
    public void handle(IstrPassedEvt evt) {
        this.instructionState = new PrPassedState<>();
    }

    @EventSourcingHandler
    public void handle(IstrPendingEvt evt) {
        this.instructionState = new PrPendingState<>();
    }


    @EventSourcingHandler
    public void handle(IstrOrderCreatedEvt evt) {
        //TODO 添加委托赋值操作
        this.orderDetail = new OrderDetail();
        this.instructionState = new ExcutingInstructionState();
    }


}
