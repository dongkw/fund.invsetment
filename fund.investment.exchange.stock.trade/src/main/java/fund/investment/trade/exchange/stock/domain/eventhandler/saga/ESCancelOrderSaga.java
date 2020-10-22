package fund.investment.trade.exchange.stock.domain.eventhandler.saga;

import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import fund.investment.trade.domain.model.eventhandler.saga.cancel.CancelOrderSaga;
import infrastructure.trade.domain.model.event.OrderCancelledEvt;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderPartialFilledCancelledEvt;

/**
 * @Author dongkw
 * @Date 2020/10/12、9:42 上午
 **/
@Saga
public class ESCancelOrderSaga extends CancelOrderSaga {
	
	@StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCancelledEvt evt) {
        startSaga(evt.getId(), evt.getInstructionId(), evt.getUnitId());
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESOrderPartialFilledCancelledEvt evt){
        startSaga(evt.getId(), evt.getInstructionId(), evt.getUnitId());
    }
}
