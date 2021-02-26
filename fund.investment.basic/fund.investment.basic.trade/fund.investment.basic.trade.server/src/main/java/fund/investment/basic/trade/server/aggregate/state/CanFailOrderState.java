package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.FailOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderFailedEvt;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
@NoArgsConstructor
public class CanFailOrderState extends CancelableOrderState {

    public CanFailOrderState(OrderStatus orderStatus) {
        super(orderStatus);
    }

    @Override
    public void fail(FailOrderCmd cmd) {
        OrderFailedEvt evt = new OrderFailedEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUnitId(),
                cmd.getFailCode(),
                cmd.getFailMsg(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                cmd.getChCancelReason());
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托失败事件:{}", evt);
    }
}
