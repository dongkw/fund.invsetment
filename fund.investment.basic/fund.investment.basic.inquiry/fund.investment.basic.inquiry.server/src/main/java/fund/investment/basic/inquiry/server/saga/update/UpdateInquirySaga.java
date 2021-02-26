package fund.investment.basic.inquiry.server.saga.update;


import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.inquiry.server.saga.InquirySaga;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplMotifyFailEvt;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplMotifySucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

@Slf4j
public abstract class UpdateInquirySaga extends InquirySaga {


    protected ITransaction transaction;


    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(InquiryCmplMotifySucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(InquiryCmplMotifyFailEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }



    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();
}
