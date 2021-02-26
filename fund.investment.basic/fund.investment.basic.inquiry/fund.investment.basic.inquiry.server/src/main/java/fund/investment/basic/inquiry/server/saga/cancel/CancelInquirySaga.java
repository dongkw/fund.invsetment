package fund.investment.basic.inquiry.server.saga.cancel;


import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.inquiry.api.event.InquiryCancelledEvt;
import fund.investment.basic.inquiry.server.saga.InquirySaga;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplCancelFailEvt;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplCancelSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

/**
 * @Author dongkw
 * @Date 2020/9/17、13:38
 **/
@Slf4j
public abstract class CancelInquirySaga extends InquirySaga {


    protected ITransaction transaction;


    @SagaEventHandler(associationProperty = "id")
    public void handler(InquiryCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }


    @SagaEventHandler(associationProperty = "id")
    public void handler(InquiryCmplCancelSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    @SagaEventHandler(associationProperty = "id")
    public void handler(InquiryCmplCancelFailEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();
}
