package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.AutoMatchOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmingFillCmd;
import fund.investment.basic.trade.api.command.MatchOrderCmd;
import fund.investment.basic.trade.api.command.PROrderMatchRollbackCmd;
import fund.investment.basic.trade.api.enumeration.OrderMatchStatus;
import fund.investment.basic.trade.api.event.OrderMatchSuccessEvt;
import fund.investment.basic.trade.api.event.PRMatchRollbackEvt;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Data
@Slf4j
public class MatchingOrderStateImpl extends OrderAggrState implements OrderMatchState {

    private OrderMatchStatus orderMatchStatus;

    public MatchingOrderStateImpl(OrderState orderState) {
        super(orderState);
    }

    @Override
    public void match(MatchOrderCmd cmd) {
        // 处理聚合根状态 修改数据库
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
    public void autoMatch(AutoMatchOrderCmd cmd) {
        OrderMatchSuccessEvt evt = new OrderMatchSuccessEvt(cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                cmd.getSkInstr(),
                null,
                cmd.getSkCombi());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托匹配事件:{}", evt);
    }

    @Override
    public void matchRollback(PROrderMatchRollbackCmd cmd) {
        PRMatchRollbackEvt evt = new PRMatchRollbackEvt();
        evt.setId(cmd.getId());
        evt.setRequestId(cmd.getRequestId());
        evt.setErrorMsg(cmd.getErrorMsg());
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void fillConfirming(ConfirmingFillCmd cmd) {
        getOrderState().fillConfirming(cmd);
    }

}
