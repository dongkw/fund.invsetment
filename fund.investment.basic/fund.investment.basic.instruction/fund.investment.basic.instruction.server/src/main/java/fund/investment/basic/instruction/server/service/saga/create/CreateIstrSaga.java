package fund.investment.basic.instruction.server.service.saga.create;


import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.instruction.server.service.saga.InstructionSaga;
import fund.investment.gateway.api.book.event.instruction.IstrVerfFailedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfRollBackedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfSucceedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplFailedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplRollbackedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

/**
 * @Author dongkw
 * @Date 2020/9/17、13:38
 **/

@Slf4j
public abstract class CreateIstrSaga extends InstructionSaga {

    protected ITransaction transaction;


    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }


    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfRollBackedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();

}
