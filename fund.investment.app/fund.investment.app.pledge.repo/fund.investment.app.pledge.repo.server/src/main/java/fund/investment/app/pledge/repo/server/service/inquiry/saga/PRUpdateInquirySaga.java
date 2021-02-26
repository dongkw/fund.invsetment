package fund.investment.app.pledge.repo.server.service.inquiry.saga;

import fund.investment.app.pledge.repo.api.command.inquiry.PRUpdateConfirmInquiryCmd;
import fund.investment.app.pledge.repo.api.command.inquiry.PRUpdateFailedInquiryCmd;
import fund.investment.app.pledge.repo.api.event.inquiry.PRInquiryUpdateEvt;
import fund.investment.app.pledge.repo.server.service.inquiry.saga.update.PRInquiryUpdateCmplTransaction;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inquiry.server.saga.update.UpdateInquirySaga;
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
public class PRUpdateInquirySaga extends UpdateInquirySaga {

    private PRInquiryUpdateEvt prInquiryUpdateEvt;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRInquiryUpdateEvt evt) {
        log.debug("update inquiry saga start,id:{}", evt.getId());
        transaction = genCreateIstrTransaction(evt);
        transaction.start();
    }

    public ITransaction genCreateIstrTransaction(PRInquiryUpdateEvt evt) {
        prInquiryUpdateEvt = evt;
        return new ParallelTransaction(Arrays.asList(new PRInquiryUpdateCmplTransaction(evt)));
    }

    @Override
    protected void checkIsFinish() {
        DomainCommand command = createCmd(transaction.getStatus());
        if (command != null) {
            CommandGatewayFactory.getCommandGateway().send(transaction.fillCmd(command));
            log.debug("saga send：{}", command);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", prInquiryUpdateEvt.getId());
        }

    }

    private DomainCommand createCmd(Status status) {
        if (status == Status.FAILED) {
            PRUpdateFailedInquiryCmd cmd = new PRUpdateFailedInquiryCmd();
            BeanUtils.copyPropertiesIgnoreNull(prInquiryUpdateEvt.getOriginalPledgeRepoInquiryAggregateVo(), cmd);
            BeanUtils.copyPropertiesIgnoreNull(prInquiryUpdateEvt.getOriginInquiryAggregateVo(), cmd);
            cmd.setRequestId(prInquiryUpdateEvt.getRequestId());
            return cmd;
        } else if (status == Status.SUCCEED) {
            PRUpdateConfirmInquiryCmd cmd = new PRUpdateConfirmInquiryCmd();
            BeanUtils.copyPropertiesIgnoreNull(prInquiryUpdateEvt, cmd);
            return cmd;
        } else {
            return null;
        }
    }
//@Saga
//@Slf4j
//public class PRUpdateInquirySaga {
//
//    private PRInquiryUpdateEvt prInquiryUpdateEvt;
//
//    @StartSaga
//    @SagaEventHandler(associationProperty = "id")
//    public void handler(PRInquiryUpdateEvt evt) {
//        log.debug("update inquiry saga start,id:{}", evt.getId());
//        this.prInquiryUpdateEvt = evt;
//        PRModifyCmplInquiryCmd cmd = new PRModifyCmplInquiryCmd();
//        BeanUtils.copyProperties(evt, cmd);
//        CommandGatewayFactory.getCommandGateway().send(cmd);
//    }
//
//    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
//    @EndSaga
//    public void handler(InquiryCmplMotifySucceedEvt evt) {
//        log.debug("saga receive:{}", evt);
//        PRUpdateConfirmInquiryCmd cmd = new PRUpdateConfirmInquiryCmd();
//        BeanUtils.copyPropertiesIgnoreNull(prInquiryUpdateEvt, cmd);
//        cmd.setChInstrSource(evt.getChInstrSource());
//        cmd.setChSourceKey(evt.getChSourceKey());
//        cmd.setChSourceNo(evt.getChSourceNo());
//        CommandGatewayFactory.getCommandGateway().send(cmd);
//    }
//
//    @EndSaga
//    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
//    public void handler(InquiryCmplMotifyFailEvt evt) {
//        log.debug("saga receive:{}", evt);
//        PRUpdateFailedInquiryCmd cmd = new PRUpdateFailedInquiryCmd();
//        BeanUtils.copyPropertiesIgnoreNull(prInquiryUpdateEvt.getOriginalPledgeRepoInquiryAggregateVo(), cmd);
//        BeanUtils.copyPropertiesIgnoreNull(prInquiryUpdateEvt.getOriginInquiryAggregateVo(), cmd);
//        cmd.setRequestId(prInquiryUpdateEvt.getRequestId());
//        cmd.setRiskRiskInfos(evt.getRiskRiskInfos());
//        CommandGatewayFactory.getCommandGateway().send(cmd);
//    }


}
