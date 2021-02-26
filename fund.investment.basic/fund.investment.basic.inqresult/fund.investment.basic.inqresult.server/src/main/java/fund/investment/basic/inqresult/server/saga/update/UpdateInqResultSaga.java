package fund.investment.basic.inqresult.server.saga.update;


import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.inqresult.server.saga.InqResultSaga;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplModifyFailedEvt;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplModifySucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

@Slf4j
public abstract class UpdateInqResultSaga extends InqResultSaga {


    protected ITransaction transaction;


    @SagaEventHandler(associationProperty = "id")
    public void handler(InqResultCmplModifySucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "id")
    public void handler(InqResultCmplModifyFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }


    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();
}
