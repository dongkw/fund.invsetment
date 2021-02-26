package fund.investment.app.pledge.repo.server.service.inqresult.saga;


import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultCancellingEvt;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.SerialTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.inqresult.server.saga.InqResultVo;
import fund.investment.basic.inqresult.server.saga.cancel.CancelInqResultSaga;
import fund.investment.basic.inqresult.server.saga.cancel.InqResultCancelCmplTranscation;
import fund.investment.basic.inqresult.server.saga.cancel.InqResultCancelTranscation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/

@Saga
@Slf4j
public class PRCancelInqResultSaga extends CancelInqResultSaga {

    private InqResultVo vo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRInquiryResultCancellingEvt evt) {
        log.debug("cancel inquiry result saga start,id:{}", evt.getId());
        transaction = genCancelTransaction(evt);
        transaction.start();
    }

    public ITransaction genCancelTransaction(PRInquiryResultCancellingEvt evt) {
        vo = createIstrVo(evt);
        return new SerialTransaction(Arrays.asList(new InqResultCancelCmplTranscation(vo), new InqResultCancelTranscation(vo, evt.getCancelType(), evt.getCancelMsg())));
    }

    private InqResultVo createIstrVo(PRInquiryResultCancellingEvt evt) {
        InqResultVo vo = new InqResultVo();
        vo.setRequestId(evt.getRequestId());
        vo.setSkId(evt.getId());
        vo.setUnitId(evt.getUnitId());
        vo.setUserId(evt.getUserId());
        vo.setSkId(evt.getSkId());
        vo.setSkInquiry(evt.getSkInquiry());
        vo.setChMemo(evt.getChMemo());
        vo.setLastmodifiedId(evt.getChLastModifiedId());
        vo.setLastmodifiedTime(new Date());
        return vo;
    }


    @Override
    protected void checkIsFinish() {
        if (transaction.getStatus() == Status.SUCCEED) {
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", vo.getSkId());
        }
    }


}
