package fund.investment.app.pledge.repo.server.service.inquiry.saga;


import fund.investment.app.pledge.repo.api.event.inquiry.PRInquiryCancellingEvt;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.SerialTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.inquiry.server.saga.InquiryVo;
import fund.investment.basic.inquiry.server.saga.cancel.CancelInquirySaga;
import fund.investment.basic.inquiry.server.saga.cancel.InquiryCancelCmplTranscation;
import fund.investment.basic.inquiry.server.saga.cancel.InquiryCancelTranscation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.Arrays;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/

@Saga
@Slf4j
public class PRCancelInquirySaga extends CancelInquirySaga {

    private InquiryVo vo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRInquiryCancellingEvt evt) {
        log.debug("cancel inquiry saga start,id:{}", evt.getId());
        transaction = genCancelTransaction(evt);
        transaction.start();
    }

    public ITransaction genCancelTransaction(PRInquiryCancellingEvt evt) {
        vo = createIstrVo(evt);
        return new SerialTransaction(Arrays.asList(new InquiryCancelCmplTranscation(vo), new InquiryCancelTranscation(vo, evt.getCancelType(), evt.getCancelMsg())));
    }

    private InquiryVo createIstrVo(PRInquiryCancellingEvt evt) {
        InquiryVo vo = new InquiryVo();
        vo.setInquiryId(evt.getId());
        vo.setSkId(evt.getSkId());
        vo.setLastmodifiedId(evt.getChLastModifiedId());
        vo.setLastmodifiedTime(evt.getTsLastModifiedTime());
        vo.setRequestId(evt.getRequestId());
        vo.setChSourceNo(evt.getChSourceNo());
        vo.setChMemo(evt.getChMemo());
        vo.setUserId(evt.getUserId());
        return vo;
    }


    @Override
    protected void checkIsFinish() {
        if (transaction.getStatus() == Status.SUCCEED) {
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", vo.getInquiryId());
        }
    }


}
