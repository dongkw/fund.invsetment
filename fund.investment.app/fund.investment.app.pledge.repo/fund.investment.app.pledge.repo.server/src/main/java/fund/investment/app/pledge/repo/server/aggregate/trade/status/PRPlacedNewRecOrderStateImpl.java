package fund.investment.app.pledge.repo.server.aggregate.trade.status;

import fund.investment.app.pledge.repo.api.event.trade.PROppositeOrderModifiedEvt;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.ConfirmingFillCmd;
import fund.investment.basic.trade.api.command.ConfirmingFillSuccessCmd;
import fund.investment.basic.trade.api.command.RejectOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.FillConfirmingEvt;
import fund.investment.basic.trade.api.event.OrderChangeConfirmingEvt;
import fund.investment.basic.trade.api.event.OrderRejectedEvt;
import fund.investment.basic.trade.server.aggregate.state.PlacedOrderState;
import fund.investment.gateway.api.compliance.command.order.ModifyOrderCmd;
import fund.investment.gateway.api.compliance.command.order.pledge.PROppositeModifyOrderCmd;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

/**
 * 新接收委托状态
 */
@Slf4j
public class PRPlacedNewRecOrderStateImpl extends PlacedOrderState {

    public PRPlacedNewRecOrderStateImpl() {
        super(OrderStatus.NEW_REC);
    }


    @Override
    public void fillConfirming(ConfirmingFillCmd cmd) {
        FillConfirmingEvt evt = new FillConfirmingEvt(cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                null);
        AggregateLifecycle.apply(evt);
        log.debug("发送成交确认中事件:{}", evt);
    }


    @Override
    public void rejectOrder(RejectOrderCmd cmd) {
        OrderRejectedEvt evt = new OrderRejectedEvt(cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime());
        BeanUtils.copyPropertiesIgnoreNull(cmd,evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送报价拒绝事件:{}", evt);
    }

    @Override
    public void oppoModify(ModifyOrderCmd cmd) {
        PROppositeModifyOrderCmd prModifyOrderCmd = (PROppositeModifyOrderCmd) cmd;
        PROppositeOrderModifiedEvt evt = new PROppositeOrderModifiedEvt();
        BeanUtils.copyProperties(prModifyOrderCmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送报价修改事件:{}", evt);
    }

    @Override
    public void confirmingSuccess(ConfirmingFillSuccessCmd cmd) {
        OrderChangeConfirmingEvt evt = new OrderChangeConfirmingEvt();
        BeanUtils.copyProperties(cmd, evt);
        AggregateLifecycle.apply(evt);
        log.debug("发送报价修改事件:{}", evt);
    }

}
