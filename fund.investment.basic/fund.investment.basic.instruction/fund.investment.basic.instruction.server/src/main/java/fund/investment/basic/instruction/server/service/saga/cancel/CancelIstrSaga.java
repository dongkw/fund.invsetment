package fund.investment.basic.instruction.server.service.saga.cancel;


import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.instruction.api.event.IstrCompletedEvt;
import fund.investment.basic.instruction.server.service.saga.InstructionSaga;
import fund.investment.basic.trade.api.event.OrderCancelledEvt;
import fund.investment.basic.trade.api.event.OrderFilledEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfCancelledEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplCancelledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;

/**
 * @Author dongkw
 * @Date 2020/9/17、13:38
 **/
@Slf4j
public abstract class CancelIstrSaga extends InstructionSaga {


    protected ITransaction transaction;


    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    @SagaEventHandler(associationProperty = "instructionId", keyName = "id")
    public void handler(OrderFilledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(IstrCompletedEvt evt) {
        log.debug("saga receive:{}", evt);
    }


//    @SagaEventHandler(associationProperty = "id")
//    public void handler(IstrCancelledEvt evt) {
//        log.debug("saga receive:{}", evt);
//        doHandler(evt);
//
//    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "id")
    public void handler(IstrCmplCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }


    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();
}
