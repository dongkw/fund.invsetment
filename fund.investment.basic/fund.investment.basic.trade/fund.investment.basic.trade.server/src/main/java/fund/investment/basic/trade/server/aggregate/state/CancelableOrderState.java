package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CancelOrderCmd;
import fund.investment.basic.trade.api.command.PlaceCancelOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderCancellingEvt;
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
        OrderCancellingEvt evt = new OrderCancellingEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUnitId(),
                cmd.getCancelQuantity(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNull(cmd,evt);
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托待撤销事件:{}", evt);
    }

    @Override
    public void delete(CancelOrderCmd cmd) {
        log.debug("已报的报价不能删除:{}", cmd);
    }

    @Override
    public void cancelling(PlaceCancelOrderCmd cmd) {
        OrderCancellingEvt evt = new OrderCancellingEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                null,
                0,
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托待撤销事件:{}", evt);
    }
}
