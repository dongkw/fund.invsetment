package fund.investment.trade.exchange.stock.domain.eventhandler.saga;

import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.CreateOrderSaga;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderCreatedEvt;

/**
 * @Author dongkw
 * @Date 2020/10/12、9:42 上午
 **/
@Saga
public class ESCreateOrderSaga extends CreateOrderSaga {

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESOrderCreatedEvt evt) {
        startSaga(evt);
    }
}
