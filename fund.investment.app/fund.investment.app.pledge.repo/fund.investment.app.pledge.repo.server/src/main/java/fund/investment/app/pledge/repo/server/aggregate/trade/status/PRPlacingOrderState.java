package fund.investment.app.pledge.repo.server.aggregate.trade.status;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.event.*;
import fund.investment.basic.trade.server.aggregate.state.PlacingOrderState;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PRPlacingOrderState extends PlacingOrderState {


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
        evt.setSkId(cmd.getSkId());
        AggregateLifecycle.apply(evt);
        log.debug("发送委托已报事件:{}", evt);
    }

    @Override
    public void changePlacing(ChangeOrderPlacingCmd cmd) {
        ChangeOrderPlacingEvt evt = new ChangeOrderPlacingEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送修改委托状态为正报事件:{}", evt);
    }

    @Override
    public void modified(ChangeModifiedOrderCmd cmd) {
        OrderChangedModifiedEvt evt = new OrderChangedModifiedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送修改委托状态为已修改事件:{}", evt);
    }

    @Override
    public void refuse(ChangeRefusedOrderCmd cmd) {
        OrderChangedrefusedEvt evt = new OrderChangedrefusedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送修改委托状态为拒绝事件:{}", evt);
    }

    @Override
    public void backPlaced(BackPlacedOrderCmd cmd) {
        OrderBackPlacedEvt evt = new OrderBackPlacedEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送回退委托状态为已报事件:{}", evt);
    }
//    @Override
//    public void modify(ModifyOrderCmd cmd) {
//        PRModifyOrderCmd prModifyOrderCmd = (PRModifyOrderCmd) cmd;
//        PROrderModifyingEvt evt = new PROrderModifyingEvt();
//        BeanUtils.copyProperties(prModifyOrderCmd, evt);
//        AggregateLifecycle.apply(evt);
//        log.debug("发送委托修改事件:{}", evt);
//    }
//
//
//    @Override
//    public void modifying(ModifyOrderCmd cmd) {
//        PRChangeModifyingOrderCmd prChangeModifyingOrderCmd = (PRChangeModifyingOrderCmd) cmd;
//        PRChangeOrderPlacingEvt evt = new PRChangeOrderPlacingEvt();
//        BeanUtils.copyProperties(prChangeModifyingOrderCmd, evt);
//        AggregateLifecycle.apply(evt);
//        log.debug("发送委托修改中事件:{}", evt);
//    }
}
