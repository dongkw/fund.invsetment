package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.BackPlacedOrderCmd;
import fund.investment.basic.trade.api.command.ChangeOrderPlacingCmd;
import fund.investment.basic.trade.api.command.ChangeRefusedOrderCmd;
import fund.investment.basic.trade.api.command.PlaceConfirmOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.ChangeOrderPlacingEvt;
import fund.investment.basic.trade.api.event.OrderBackPlacedEvt;
import fund.investment.basic.trade.api.event.OrderChangedrefusedEvt;
import fund.investment.basic.trade.api.event.OrderPlacedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PlacingOrderState extends CanFailOrderState {

    public PlacingOrderState() {
        super(OrderStatus.PLACING);
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

//    @Override
//    public void modified(ChangeModifiedOrderCmd cmd) {
//        OrderChangedModifiedEvt evt = new OrderChangedModifiedEvt();
//        BeanUtils.copyProperties(cmd, evt);
//        AggregateLifecycle.apply(evt);
//        log.debug("发送修改委托状态为已修改事件:{}", evt);
//    }

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

}
