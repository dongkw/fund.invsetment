package fund.investment.instruction.domain.model.entity;

import fund.investment.infrastructure.instruction.domain.model.enumeration.OrderStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrFillReceivedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCreatedEvt;
import fund.investment.infrastructure.instruction.domain.model.valueobject.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    private OrderStatus status = OrderStatus.UNDEFINED;

    private long orderQuantity;

    private long fillQuantity;

    private long cancelQuantity;

    List<Order> orders = new LinkedList<>();

    public void createOrder(IstrOrderCreatedEvt istrOrderCreatedEvt) {
        this.orderQuantity += istrOrderCreatedEvt.getOrderQuantity();
    }

    public boolean cancelOrder(IstrOrderCancelledEvt istrOrderCancelledEvt) {
        this.cancelQuantity += istrOrderCancelledEvt.getCancelQuantity();
        this.orderQuantity -= istrOrderCancelledEvt.getCancelQuantity();
        if (this.orderQuantity == 0) {
            return true;
        }
        return false;
    }

    public void receiveFill(IstrFillReceivedEvt istrFillReceivedEvt) {
        this.fillQuantity += istrFillReceivedEvt.getFillQuantity();
    }
}
