package fund.investment.trade.domain.model.aggregate.state;

import infrastructure.trade.domain.model.command.CancelOrderCmd;
import infrastructure.trade.domain.model.command.PlaceCancelOrderCmd;
import infrastructure.trade.domain.model.enumeration.OrderStatus;
import infrastructure.trade.domain.model.event.OrderCancellingEvt;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
@NoArgsConstructor
public class CancelableOrderState extends OrderState {

    public CancelableOrderState(OrderStatus orderStatus) {
        super(orderStatus);
    }

    @Override
    public void cancel(CancelOrderCmd cmd) {
        log.info("Recieved Command: {}", cmd);

        OrderCancellingEvt evt = new OrderCancellingEvt(
                cmd.getId(),
                cmd.getInstructionId(),
                cmd.getTradeType());
        AggregateLifecycle.apply(evt);
        log.info("Dispached Event: {}", evt);
    }

    @Override
    public void cancelling(PlaceCancelOrderCmd cmd) {
        log.info("Recieved Command: {}", cmd);

        OrderCancellingEvt evt = new OrderCancellingEvt(
                cmd.getId(),
                cmd.getInstructionId(),
                cmd.getTradeType());
        AggregateLifecycle.apply(evt);
        log.info("Dispached Event: {}", evt);
    }
}
