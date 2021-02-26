package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.CancelConfirmOrderCmd;
import fund.investment.basic.trade.api.command.FillOrderCmd;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.api.event.OrderFilledEvt;
import fund.investment.basic.trade.api.event.OrderPartialFilledCancelledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class PFCancellingOrderState extends OrderState {

    public PFCancellingOrderState() {
        super(OrderStatus.PARTIAL_FILLED_CANCELLING);
    }

    public void cancelConfirm(CancelConfirmOrderCmd cmd) {
        OrderPartialFilledCancelledEvt evt = new OrderPartialFilledCancelledEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUnitId(),
                cmd.getCancelQuantity());
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void fill(FillOrderCmd cmd) {
        OrderFilledEvt evt = new OrderFilledEvt(
                cmd.getId(),
                cmd.getTradeType(),
                cmd.getInstructionId(),
                cmd.getUserId(),
                cmd.getSkId(),
                cmd.getChLastModifiedId(),
                cmd.getTsLastModifiedTime(),
                cmd.getFill());
        AggregateLifecycle.apply(evt);
    }
}
