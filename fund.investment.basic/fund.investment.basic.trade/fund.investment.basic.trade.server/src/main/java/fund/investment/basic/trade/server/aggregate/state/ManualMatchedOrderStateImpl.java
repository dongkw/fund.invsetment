package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CancelMatchOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmingFillCmd;
import fund.investment.basic.trade.api.command.PRCancelMatchOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderMatchStatus;
import fund.investment.basic.trade.api.event.OrderCancelMatchSuccessEvt;
import fund.investment.basic.trade.api.event.PROrderUnmatchingEvt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Data
@Slf4j
public class ManualMatchedOrderStateImpl extends OrderAggrState implements OrderMatchState {

    private OrderMatchStatus orderMatchStatus;

    public ManualMatchedOrderStateImpl(OrderState orderState) {
        super(orderState);
    }


    @Override
    public void cancelMatch(CancelMatchOrderCmd cmd) {
        OrderCancelMatchSuccessEvt evt = new OrderCancelMatchSuccessEvt();
        evt.setId(cmd.getId());
        evt.setSkId(cmd.getSkId());
        evt.setRequestId(cmd.getRequestId());
        evt.setChLastModifiedId(cmd.getChLastModifiedId());
        evt.setInstrSkId(cmd.getInstructionId());
        evt.setTradeType(cmd.getTradeType());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托撤销匹配成功事件:{}", evt);
    }

    @Override
    public void unmatching(PRCancelMatchOrderCmd cmd) {
        PROrderUnmatchingEvt evt = new PROrderUnmatchingEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }


    @Override
    public void fillConfirming(ConfirmingFillCmd cmd) {
        getOrderState().fillConfirming(cmd);
    }

}
