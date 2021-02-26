package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.FillOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderFilledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PartialFilledOrderState extends CancelableOrderState {

    public PartialFilledOrderState() {
        super(OrderStatus.PARTIAL_FILLED);
    }

    @Override
    public void fill(FillOrderCmd cmd) {
        OrderFilledEvt evt = new OrderFilledEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                cmd.getFill());
        AggregateLifecycle.apply(evt);
    }
}
