package fund.investment.basic.inqresult.server.saga.create;


import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.inqresult.server.saga.InqResultSaga;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplCommitFailedEvt;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplCommitSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;

/**
 * @Author dongkw
 * @Date 2020/9/17、13:38
 * todo 暂时用指令和合规记账
 **/


@Slf4j
public abstract class CreateInqResultSaga extends InqResultSaga {

    protected ITransaction transaction;


    @SagaEventHandler(associationProperty = "id")
    public void handler(InqResultCmplCommitSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }

    @SagaEventHandler(associationProperty = "id")
    public void handler(InqResultCmplCommitFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);

    }


    protected void doHandler(DomainEvent evt) {
        transaction.eventHandler(evt);
        checkIsFinish();
    }

    protected abstract void checkIsFinish();

}
