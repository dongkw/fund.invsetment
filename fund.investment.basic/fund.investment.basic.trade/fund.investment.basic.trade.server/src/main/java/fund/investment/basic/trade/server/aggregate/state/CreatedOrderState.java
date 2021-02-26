package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.CancelOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmOrderCmd;
import fund.investment.basic.trade.api.command.PlaceCancelOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderConfirmedEvt;
import fund.investment.basic.trade.api.event.OrderDeleteEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CreatedOrderState extends CanFailOrderState {

    public CreatedOrderState() {
        super(OrderStatus.CREATED);
    }

    @Override
    public void cancel(CancelOrderCmd cmd) {
        log.debug("只创建完成的委托不需要发送撤销委托:{}", cmd);
    }

    @Override
    public void cancelling(PlaceCancelOrderCmd cmd) {
        log.debug("只创建完成的委托不需要发送撤销委托:{}", cmd);
    }


    @Override
    public void delete(CancelOrderCmd cmd) {
        OrderDeleteEvt evt = new OrderDeleteEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托删除事件:{}", evt);
    }

    @Override
    public void createConfirm(ConfirmOrderCmd cmd) {
        OrderConfirmedEvt evt = new OrderConfirmedEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime());
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托已确认创建事件:{}", evt);
    }

}
