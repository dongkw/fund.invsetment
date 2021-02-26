package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.MatchOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderMatchStatus;
import fund.investment.basic.trade.api.event.OrderMatchSuccessEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CancelMatchedOrderStateImpl extends OrderAggrState {

    private OrderMatchStatus orderMatchStatus;

    public CancelMatchedOrderStateImpl(OrderState orderState) {
        super(orderState);
        this.orderMatchStatus = OrderMatchStatus.CANCELED;
    }

    @Override
    public void match(MatchOrderCmd cmd) {
        OrderMatchSuccessEvt evt = new OrderMatchSuccessEvt(cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                cmd.getInstrSkId(),
                cmd.getInstrSkInstr(),
                cmd.getSkCombi());
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托匹配事件:{}", evt);
    }
}
