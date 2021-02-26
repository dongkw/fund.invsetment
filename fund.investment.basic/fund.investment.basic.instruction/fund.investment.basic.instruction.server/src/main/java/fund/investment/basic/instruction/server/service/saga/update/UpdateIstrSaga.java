package fund.investment.basic.instruction.server.service.saga.update;


import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.gateway.api.book.event.instruction.IstrVerfUpdateFailedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfUpdateRollBackedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfUpdateSucceedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplUpdateFailedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplUpdateRollbackedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplUpdateSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

@Slf4j
public abstract class UpdateIstrSaga {


    protected ITransaction transaction;


    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplUpdateSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplUpdateFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfUpdateSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfUpdateFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }


    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplUpdateRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfUpdateRollBackedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();
}
