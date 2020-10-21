package fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.cancel;

import fund.investment.infrastructure.book.domain.model.command.order.CancelVerfOrderCmd;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfCancelledEvt;
import fund.investment.infrastructure.compliance.domain.model.command.order.CancelCmplOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrOrderCmd;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;

import com.fasterxml.jackson.annotation.JsonProperty;

import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.OrderSaga;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author dongkw
 * @Date 2020/10/12、10:24 上午
 **/
@Slf4j
public class CancelOrderSaga extends OrderSaga {

    @JsonProperty
    private String orderId;
    @JsonProperty
    private String istrId;
    @JsonProperty
    private String unitId;

    @JsonProperty
    private boolean cancelCmpl;
    @JsonProperty
    private boolean cancelIstr;
    @JsonProperty
    private boolean cancelVref;

    public void startSaga(String orderId, String istrId, String unitId) {
        this.orderId = orderId;
        this.istrId = istrId;
        this.unitId = unitId;
        CancelCmplOrderCmd cancelCmplOrderCmd = new CancelCmplOrderCmd(unitId, orderId);
        commandGateway.send(cancelCmplOrderCmd);
        log.debug("saga send:{}", cancelCmplOrderCmd);
        ESCancelIstrOrderCmd esCancelIstrOrderCmd=new ESCancelIstrOrderCmd();
        esCancelIstrOrderCmd.setId(istrId);
        esCancelIstrOrderCmd.setOrderId(orderId);
        CancelIstrOrderCmd cancelIstrOrderCmd = CancelIstrOrderCmd.builder().orderId(orderId).build();
        cancelIstrOrderCmd.setId(istrId);
        commandGateway.send(esCancelIstrOrderCmd);
        log.debug("saga send:{}", esCancelIstrOrderCmd);
        CancelVerfOrderCmd cancelVerfOrderCmd = new CancelVerfOrderCmd(unitId, orderId);
        commandGateway.send(cancelVerfOrderCmd);
        log.debug("saga send:{}", cancelVerfOrderCmd);
    }

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
