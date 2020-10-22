package fund.investment.trade.domain.model.aggregate.state;

import infrastructure.trade.domain.model.command.PlaceConfirmOrderCmd;
import infrastructure.trade.domain.model.enumeration.OrderStatus;
import infrastructure.trade.domain.model.event.OrderPlacedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PlacingOrderState extends CanFailOrderState {

    public PlacingOrderState() {
        super(OrderStatus.PLACING);
    }

    @Override
    public void placed(PlaceConfirmOrderCmd cmd) {
        log.info("Recieved Command: {}", cmd);
        OrderPlacedEvt evt = new OrderPlacedEvt(
                cmd.getId(),
                cmd.getInstructionId(),
                cmd.getTradeType());
        AggregateLifecycle.apply(evt);
        log.info("Dispached Event: {}", evt);
    }
}
