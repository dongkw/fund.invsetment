package fund.investment.approval.domain.aggregate;

import fund.investment.infrastructure.approve.domain.command.AprvIstrInitializationCmd;
import fund.investment.infrastructure.approve.domain.command.AprvIstrPassCmd;
import fund.investment.infrastructure.approve.domain.command.AprvIstrRejectedCmd;
import fund.investment.infrastructure.approve.domain.event.AprvIstrInitializationEvt;
import fund.investment.infrastructure.approve.domain.event.AprvIstrPassEvt;
import fund.investment.infrastructure.approve.domain.event.AprvIstrRejectedEvt;
import fund.investment.infrastructure.instruction.domain.model.enumeration.ApprovalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Data
@Slf4j
@Aggregate
@NoArgsConstructor
public class ApprovalIstrAggregate {

    @AggregateIdentifier
    private String id;

    private String instructionId;

    private String userId;

    private String operatorId;

    @CommandHandler
    public ApprovalIstrAggregate(AprvIstrInitializationCmd cmd) {
        log.info(cmd.toString());
        AggregateLifecycle.apply(
                new AprvIstrInitializationEvt(cmd.getId(), cmd.getInstructionId(), ApprovalStatus.WAIT.getId(), cmd.getUserId(), cmd.getOperatorId()));
    }

    @CommandHandler
    public void on(AprvIstrPassCmd cmd) {
        log.info(cmd.toString());
        AggregateLifecycle.apply(
                new AprvIstrPassEvt(cmd.getId(), cmd.getInstructionId(), ApprovalStatus.PASS.getId(), cmd.getUserId(), cmd.getOperatorId()));
    }

    @CommandHandler
    public void on(AprvIstrRejectedCmd cmd) {
        log.info(cmd.toString());
        AggregateLifecycle.apply(
                new AprvIstrRejectedEvt(cmd.getId(), cmd.getInstructionId(), ApprovalStatus.REJECT.getId(), cmd.getUserId(), cmd.getOperatorId()));
    }

    @EventSourcingHandler
    public void action(AprvIstrInitializationEvt evt) {
        this.id = evt.getId();
        this.instructionId = evt.getInstructionId();
        this.userId = evt.getUserId();
        this.operatorId = evt.getOperatorId();
    }

    @EventSourcingHandler
    public void action(AprvIstrPassEvt evt) {
        this.id = evt.getId();
        this.instructionId = evt.getInstructionId();
        this.userId = evt.getUserId();
        this.operatorId = evt.getOperatorId();
    }

    @EventSourcingHandler
    public void action(AprvIstrRejectedCmd evt) {
        this.id = evt.getId();
        this.instructionId = evt.getInstructionId();
        this.userId = evt.getUserId();
        this.operatorId = evt.getOperatorId();

    }

}
