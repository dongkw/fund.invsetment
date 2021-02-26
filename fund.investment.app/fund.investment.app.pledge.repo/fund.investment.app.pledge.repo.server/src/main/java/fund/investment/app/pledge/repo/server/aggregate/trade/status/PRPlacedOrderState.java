package fund.investment.app.pledge.repo.server.aggregate.trade.status;

import fund.investment.app.pledge.repo.api.command.trade.PRChangeModifyingOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRModifyOrderCmd;
import fund.investment.app.pledge.repo.api.event.trade.PRChangeOrderPlacingEvt;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.ChangeModifiedOrderCmd;
import fund.investment.basic.trade.api.command.ChangeRefusedOrderCmd;
import fund.investment.basic.trade.api.command.ConfirmRejectOrderCmd;
import fund.investment.basic.trade.api.command.PlaceConfirmOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderChangedModifiedEvt;
import fund.investment.basic.trade.api.event.OrderChangedrefusedEvt;
import fund.investment.basic.trade.api.event.OrderPlacedEvt;
import fund.investment.basic.trade.api.event.OrderRejectConfirmedEvt;
import fund.investment.basic.trade.server.aggregate.state.PlacedOrderState;
import fund.investment.gateway.api.compliance.command.order.ModifyOrderCmd;
import fund.investment.gateway.api.compliance.event.order.pledge.PROrderModifyingEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PRPlacedOrderState extends PlacedOrderState {

    public PRPlacedOrderState() {
        super(OrderStatus.PLACED);
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

    @Override
    public void modify(ModifyOrderCmd cmd) {
        PRModifyOrderCmd prModifyOrderCmd = (PRModifyOrderCmd) cmd;
        PROrderModifyingEvt evt = new PROrderModifyingEvt();
        BeanUtils.copyProperties(prModifyOrderCmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送委托修改事件:{}", evt);
    }


    @Override
    public void modifying(ModifyOrderCmd cmd) {
        PRChangeModifyingOrderCmd prChangeModifyingOrderCmd = (PRChangeModifyingOrderCmd) cmd;
        PRChangeOrderPlacingEvt evt = new PRChangeOrderPlacingEvt();
        BeanUtils.copyProperties(prChangeModifyingOrderCmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送委托修改中事件:{}", evt);
    }

    @Override
    public void refuse(ChangeRefusedOrderCmd cmd) {
        OrderChangedrefusedEvt evt = new OrderChangedrefusedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送修改委托状态为拒绝事件:{}", evt);
    }

    @Override
    public void modified(ChangeModifiedOrderCmd cmd) {
        OrderChangedModifiedEvt evt = new OrderChangedModifiedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送修改委托状态为已修改事件:{}", evt);
    }

    @Override
    public void placed(PlaceConfirmOrderCmd cmd) {
        OrderPlacedEvt evt = new OrderPlacedEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                cmd.getChReportNo(),
                cmd.getChCancelReason());
        evt.setChEntrustStatus(cmd.getChEntrustStatus());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托已报事件:{}", evt);
    }

}
