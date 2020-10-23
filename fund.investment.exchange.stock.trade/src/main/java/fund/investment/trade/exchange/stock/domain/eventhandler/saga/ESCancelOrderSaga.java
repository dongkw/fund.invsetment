package fund.investment.trade.exchange.stock.domain.eventhandler.saga;

import fund.investment.infrastructure.book.domain.model.command.order.CancelVerfOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.command.order.CancelCmplOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.command.CancelIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrOrderCmd;
import fund.investment.trade.domain.model.eventhandler.saga.cancel.CancelOrderSaga;
import infrastructure.trade.domain.model.event.OrderCancelledEvt;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderPartialFilledCancelledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

/**
 * @Author dongkw
 * @Date 2020/10/12、9:42 上午
 **/
@Saga
@Slf4j
public class ESCancelOrderSaga extends CancelOrderSaga {


    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCancelledEvt evt) {
        startSaga(evt.getId(), evt.getInstructionId(), evt.getUnitId());
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESOrderPartialFilledCancelledEvt evt) {
        startSaga(evt.getId(), evt.getInstructionId(), evt.getUnitId());
    }

    public void startSaga(String orderId, String istrId, String unitId) {
        setOrderId(orderId);
        setIstrId(istrId);
        setUnitId(unitId);
        CancelCmplOrderCmd cancelCmplOrderCmd = new CancelCmplOrderCmd(unitId, orderId);
        commandGateway.send(cancelCmplOrderCmd);
        log.debug("saga send:{}", cancelCmplOrderCmd);
        ESCancelIstrOrderCmd esCancelIstrOrderCmd = new ESCancelIstrOrderCmd();
        esCancelIstrOrderCmd.setId(istrId);
        esCancelIstrOrderCmd.setOrderId(orderId);
        CancelIstrOrderCmd cancelIstrOrderCmd = new CancelIstrOrderCmd();
        cancelIstrOrderCmd.setOrderId(orderId);
        cancelIstrOrderCmd.setId(istrId);
        commandGateway.send(esCancelIstrOrderCmd);
        log.debug("saga send:{}", esCancelIstrOrderCmd);
        CancelVerfOrderCmd cancelVerfOrderCmd = new CancelVerfOrderCmd(unitId, orderId);
        commandGateway.send(cancelVerfOrderCmd);
        log.debug("saga send:{}", cancelVerfOrderCmd);
    }
}
