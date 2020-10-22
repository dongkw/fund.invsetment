package fund.investment.exchange.stock.domain.eventhandler.saga;

import fund.investment.instruction.domain.model.eventhandler.saga.util.create.CreateIstrSaga;
import fund.investment.instruction.exchange.stock.domain.model.event.ESIstrCreatedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/
@Saga
@Slf4j
public class ESCreateIstrSaga extends CreateIstrSaga {

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESIstrCreatedEvt evt) {
        startSaga(evt);
    }
}
