package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CancelConfirmOrderCmd;
import fund.investment.basic.trade.api.command.FillOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderCancelledEvt;
import fund.investment.basic.trade.api.event.OrderFilledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CancellingOrderState extends OrderState {

    public CancellingOrderState() {
        super(OrderStatus.CANCELLING);
    }

    public CancellingOrderState(OrderStatus orderStatus) {
        super(orderStatus);
    }

    @Override
    public void cancelConfirm(CancelConfirmOrderCmd cmd) {
        OrderCancelledEvt evt = new OrderCancelledEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUnitId(),
                cmd.getCancelQuantity(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime());
        evt.setChEntrustStatus(cmd.getChEntrustStatus());
        BeanUtils.copyPropertiesIgnoreNull(cmd,evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送委托已撤销事件:{}", evt);
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
        log.debug("发送委托成交事件:{}", evt);
    }
}
