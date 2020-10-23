package fund.investment.trade.domain.model.eventhandler.saga.cancel;

import com.fasterxml.jackson.annotation.JsonProperty;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfCancelledEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrOrderCmd;
import fund.investment.trade.domain.model.eventhandler.saga.OrderSaga;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CancelOrderSaga extends OrderSaga {

    @Setter
    @JsonProperty
    private String orderId;

    @Setter
    @JsonProperty
    private String istrId;

    @Setter
    @JsonProperty
    private String unitId;

    @JsonProperty
    private boolean cancelCmpl;

    @JsonProperty
    private boolean cancelIstr;

    @JsonProperty
    private boolean cancelVref;


    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        cancelCmpl = true;
        endSaga();
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(CancelIstrOrderCmd evt) {
        log.debug("saga receive:{}", evt);
        cancelIstr = true;
        endSaga();
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        cancelVref = true;
        endSaga();
    }

    private void endSaga() {
        if (cancelCmpl && cancelVref && cancelIstr) {
            SagaLifecycle.end();
            log.debug("saga end：{}", orderId);
            log.debug("---------------------");
        }
    }
}
