package fund.investment.trade.domain.model.aggregate.state;

import infrastructure.trade.domain.model.command.FailOrderCmd;
import infrastructure.trade.domain.model.enumeration.OrderStatus;
import infrastructure.trade.domain.model.event.OrderFailedEvt;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
@NoArgsConstructor
public class CanFailOrderState extends CancelableOrderState {

    public CanFailOrderState(OrderStatus orderStatus) {
        super(orderStatus);
    }

    public void fail(FailOrderCmd cmd) {
        log.info("Recieved Command: {}", cmd);

        OrderFailedEvt evt = new OrderFailedEvt(
                cmd.getId(),
                cmd.getInstructionId(),
                cmd.getTradeType(),
                cmd.getUnitId(),
                cmd.getFailCode(),
                cmd.getFailMsg());
        AggregateLifecycle.apply(evt);
        log.info("Dispached Event: {}", evt);
    }
}
