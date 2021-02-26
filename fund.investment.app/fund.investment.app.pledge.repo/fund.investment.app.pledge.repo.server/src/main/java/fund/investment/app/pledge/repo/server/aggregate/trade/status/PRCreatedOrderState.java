package fund.investment.app.pledge.repo.server.aggregate.trade.status;

import fund.investment.app.pledge.repo.api.command.trade.PRModifyOrderCmd;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.server.aggregate.state.CreatedOrderState;
import fund.investment.gateway.api.compliance.command.order.ModifyOrderCmd;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderDraModifyingEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PRCreatedOrderState extends CreatedOrderState {

    @Override
    public void modify(ModifyOrderCmd cmd) {
        PRModifyOrderCmd prModifyOrderCmd = (PRModifyOrderCmd) cmd;
        PROrderDraModifyingEvt evt = new PROrderDraModifyingEvt();
        BeanUtils.copyProperties(prModifyOrderCmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送委托修改事件:{}", evt);
    }
}
