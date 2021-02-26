package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.ConfirmedFillCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.FillConfirmedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * 成交确认状态
 */
@Slf4j
public class FillConfirmingState extends OrderState {

    public FillConfirmingState() {
        super(OrderStatus.CONFIRMING);
    }


    @Override
    public void fillConfirm(ConfirmedFillCmd cmd) {
        FillConfirmedEvt evt = new FillConfirmedEvt(cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                null);
        AggregateLifecycle.apply(evt);
        log.debug("发送成交确认事件:{}", evt);
    }
}
