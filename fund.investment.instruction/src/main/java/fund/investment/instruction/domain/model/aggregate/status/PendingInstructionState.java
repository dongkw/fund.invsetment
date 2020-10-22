package fund.investment.instruction.domain.model.aggregate.status;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.InstructionStatus;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCreatedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderFailedEvt;
import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import fund.investment.instruction.domain.model.entity.IstrTradeElement;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PendingInstructionState extends CancelableInstructionState {

    public PendingInstructionState() {
        super(InstructionStatus.PENDING);
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
}
