package fund.investment.basic.inqresult.server.saga.cancel;


import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.inqresult.api.event.InquiryResultCancelledEvt;
import fund.investment.basic.inqresult.server.saga.InqResultSaga;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplCancelFailedEvt;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplCancelSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

/**
 * @Author dongkw
 * @Date 2020/9/17、13:38
 **/
@Slf4j
public abstract class CancelInqResultSaga extends InqResultSaga {


    protected ITransaction transaction;

    @SagaEventHandler(associationProperty = "id")
    public void handler(InquiryResultCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "id")
    public void handler(InqResultCmplCancelSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "id")
    public void handler(InqResultCmplCancelFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }


    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();
}
