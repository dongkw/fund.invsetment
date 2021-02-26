package fund.investment.basic.trade.server.aggregate.state;

import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.enumeration.OrderStatus;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import fund.investment.gateway.api.compliance.command.order.ModifyOrderCmd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderState implements OrderMatchState {

    private OrderStatus orderStatus;

//    private OrderMatchState orderMatchState;

    public void createConfirm(ConfirmOrderCmd cmd) {
    }

    public void delete(CancelOrderCmd cmd) {
    }

    public void fail(FailOrderCmd cmd) {
    }

    public void placing(PlaceOrderCmd cmd, OrderAggregate orderAggregate) {
    }

    public void placed(PlaceConfirmOrderCmd cmd) {
    }

    public void cancel(CancelOrderCmd cmd) {
    }

    public void cancelling(PlaceCancelOrderCmd cmd) {
    }

    public void cancelConfirm(CancelConfirmOrderCmd cmd) {
    }

    public void fill(FillOrderCmd cmd) {
    }

    public void modify(ModifyOrderCmd cmd) {
    }

    public void oppoModify(ModifyOrderCmd cmd) {
    }

    public void fillConfirming(ConfirmingFillCmd cmd) {
    }

    public void fillConfirm(ConfirmedFillCmd cmd) {
    }

    public void rejectOrder(RejectOrderCmd cmd) {
    }

    public void rejectOrderConfirm(ConfirmRejectOrderCmd cmd) {
    }

    public void changePlacing(ChangeOrderPlacingCmd cmd) {
    }

    public void modifying(ModifyOrderCmd cmd) {
    }

    public void modified(ChangeModifiedOrderCmd cmd) {
    }

    public void refuse(ChangeRefusedOrderCmd cmd) {
    }

    public void backPlaced(BackPlacedOrderCmd cmd) {
    }

    public void confirmingSuccess(ConfirmingFillSuccessCmd cmd) {
    }
}
