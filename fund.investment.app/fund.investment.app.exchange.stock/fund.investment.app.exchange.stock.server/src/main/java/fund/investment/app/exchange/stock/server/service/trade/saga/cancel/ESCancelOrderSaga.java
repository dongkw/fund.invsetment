package fund.investment.app.exchange.stock.server.service.trade.saga.cancel;


import fund.investment.app.exchange.stock.api.event.trade.ESOrderPartialFilledCancelledEvt;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.instruction.api.event.IstrOrderCancelledEvt;
import fund.investment.basic.trade.api.event.OrderCancelledEvt;
import fund.investment.basic.trade.server.saga.OrderVo;
import fund.investment.basic.trade.server.saga.cancel.CancelOrderSaga;
import fund.investment.basic.trade.server.saga.cancel.OrderCancelCmplTransaction;
import fund.investment.basic.trade.server.saga.cancel.OrderCancelVerfTransaction;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dongkw
 * @Date 2020/10/12、9:42 上午
 **/
@Saga
@Slf4j
public class ESCancelOrderSaga extends CancelOrderSaga {

    private OrderVo vo;

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

        transaction = createTransaction(orderId, istrId, unitId);
        transaction.start();
    }

    private ITransaction createTransaction(String orderId, String istrId, String unitId) {
        vo = new OrderVo();
        vo.setOrderId(orderId);
        vo.setIstrId(istrId);
        vo.setUnitId(unitId);
        List<ITransaction> list = new ArrayList<>();
        list.add(new OrderCancelESIstrTransaction(vo));
        list.add(new OrderCancelCmplTransaction(vo));
        list.add(new OrderCancelVerfTransaction(vo));
        return new ParallelTransaction(list);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @Override
    protected void checkIsFinish() {
        if (transaction.getStatus() == Status.SUCCEED) {
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}");
        }
    }
}
