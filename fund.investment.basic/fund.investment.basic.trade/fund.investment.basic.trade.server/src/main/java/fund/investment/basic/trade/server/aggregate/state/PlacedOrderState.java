package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.FillOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderFilledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PlacedOrderState extends CanFailOrderState {

    public PlacedOrderState() {
        super(OrderStatus.PLACED);
    }

    public PlacedOrderState(OrderStatus orderStatus) {
        super(orderStatus);
    }

    /**
     * 成交委托
     *
     * @param cmd
     */
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
        evt.setChEntrustStatus(cmd.getChEntrustStatus());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托成交事件:{}", evt);
    }
}
