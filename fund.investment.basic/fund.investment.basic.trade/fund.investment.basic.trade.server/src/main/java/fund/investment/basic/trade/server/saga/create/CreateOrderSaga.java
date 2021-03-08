package fund.investment.basic.trade.server.saga.create;

import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.trade.server.saga.OrderSaga;
import fund.investment.gateway.api.book.event.order.OrderVerfFailedEvt;
import fund.investment.gateway.api.book.event.order.OrderVerfRollbackedEvt;
import fund.investment.gateway.api.book.event.order.OrderVerfSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

@Slf4j
public abstract class CreateOrderSaga extends OrderSaga {

    protected ITransaction transaction;


    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();
}
