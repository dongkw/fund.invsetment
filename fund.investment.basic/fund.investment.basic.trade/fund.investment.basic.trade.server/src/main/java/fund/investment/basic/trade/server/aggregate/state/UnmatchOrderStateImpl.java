package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.AutoMatchOrderCmd;
import fund.investment.basic.trade.api.command.MatchOrderCmd;
import fund.investment.basic.trade.api.command.PRMatchOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderMatchStatus;
import fund.investment.basic.trade.api.event.OrderAutoMatchedEvt;
import fund.investment.basic.trade.api.event.OrderMatchSuccessEvt;
import fund.investment.basic.trade.api.event.PROrderMatchingEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class UnmatchOrderStateImpl extends OrderAggrState {

    private OrderMatchStatus orderMatchStatus;

    public UnmatchOrderStateImpl(OrderState orderState) {
        super(orderState);
        this.orderMatchStatus = OrderMatchStatus.UNMATCH;
    }

    public OrderMatchStatus getOrderMatchStatus() {
        return orderMatchStatus;
    }

    public void setOrderMatchStatus(OrderMatchStatus orderMatchStatus) {
        this.orderMatchStatus = orderMatchStatus;
    }

    @Override
    public void autoMatch(AutoMatchOrderCmd cmd) {
        OrderAutoMatchedEvt evt = new OrderAutoMatchedEvt(cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                cmd.getSkInstr(),
                cmd.getSkCombi());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托匹配事件:{}", evt);
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

    @Override
    public void matching(PRMatchOrderCmd cmd) {
        PROrderMatchingEvt evt = new PROrderMatchingEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
