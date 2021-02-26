package fund.investment.basic.trade.server.saga.cancel;

import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.trade.server.saga.OrderSaga;
import fund.investment.gateway.api.book.event.order.OrderVerfCancelledEvt;
import fund.investment.gateway.api.compliance.event.order.OrderCmplCancelledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

@Slf4j
public abstract class CancelOrderSaga extends OrderSaga {

    public ITransaction transaction;


    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();
}
