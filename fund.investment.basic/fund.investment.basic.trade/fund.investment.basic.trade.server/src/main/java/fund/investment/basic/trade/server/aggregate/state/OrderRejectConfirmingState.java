package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.ConfirmRejectOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderRejectConfirmedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * 成交确认状态
 */
@Slf4j
public class OrderRejectConfirmingState extends CancellingOrderState {

    public OrderRejectConfirmingState() {
        super(OrderStatus.REJECT_CONFIRMING);
    }

    @Override
    public void rejectOrderConfirm(ConfirmRejectOrderCmd cmd) {
        OrderRejectConfirmedEvt evt = new OrderRejectConfirmedEvt(cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime());
        AggregateLifecycle.apply(evt);
        log.debug("发送报价拒绝确认事件:{}", evt);
    }

}
