package fund.investment.app.pledge.repo.server.aggregate.trade.status;

import fund.investment.app.pledge.repo.api.command.trade.PRModifyOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRPlaceOrderCmd;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.PlaceOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.basic.trade.server.aggregate.state.ConfirmedOrderState;
import fund.investment.gateway.api.compliance.command.order.ModifyOrderCmd;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderDraModifyingEvt;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderPlacingEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PRConfirmedOrderState extends ConfirmedOrderState {

    public PRConfirmedOrderState() {
        super(OrderStatus.CONFIRMED);
    }

    @Override
    public void placing(PlaceOrderCmd cmd, OrderAggregate orderAggregate) {
        PROrderPlacingEvt evt = new PROrderPlacingEvt();
        PRPlaceOrderCmd prPlaceOrderCmd = (PRPlaceOrderCmd) cmd;
        BeanUtils.copyProperties(prPlaceOrderCmd,evt);
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托发送中事件:{}", evt);
    }

    @Override
    public void modify(ModifyOrderCmd cmd) {
        PRModifyOrderCmd prModifyOrderCmd = (PRModifyOrderCmd) cmd;
        PROrderDraModifyingEvt evt = new PROrderDraModifyingEvt();
        BeanUtils.copyProperties(prModifyOrderCmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送委托修改事件:{}", evt);
    }

}
