package fund.investment.app.pledge.repo.server.service.inqresult.saga;

import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateInquiryResultDraCmd;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultDraUpdateEvt;
import fund.investment.gateway.api.compliance.command.inqresult.pledge.PRModifyDraCmplInqResultCmd;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplDraModifyFailedEvt;
import fund.investment.gateway.api.compliance.event.inqresult.InqResultCmplDraModifySucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/
@Saga
@Slf4j
public class PRUpdateDraInqResultSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    private PRInquiryResultDraUpdateEvt vo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRInquiryResultDraUpdateEvt evt) {
        log.debug("修改询价结果草稿saga start,id:{}", evt.getId());
        PRModifyDraCmplInqResultCmd cmd = new PRModifyDraCmplInqResultCmd();
        BeanUtils.copyProperties(evt, cmd);
        this.vo=evt;
        commandGateway.send(cmd);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    private void handler(InqResultCmplDraModifySucceedEvt evt) {
        PRUpdateInquiryResultDraCmd cmd = new PRUpdateInquiryResultDraCmd();
        BeanUtils.copyProperties(this.vo, cmd);
        commandGateway.send(cmd);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    private void handler(InqResultCmplDraModifyFailedEvt evt) {


    }
}
