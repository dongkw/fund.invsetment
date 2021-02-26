package fund.investment.app.pledge.repo.server.service.inqresult.saga;

import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateConfirmInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateFailedInquiryResultCmd;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateEvt;
import fund.investment.app.pledge.repo.server.service.inqresult.saga.update.PRInqResultUpdateTransaction;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inqresult.server.saga.update.UpdateInqResultSaga;
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
public class PRUpdateInqResultSaga extends UpdateInqResultSaga {

    private PRInquiryResultUpdateEvt prInquiryResultUpdateEvt;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRInquiryResultUpdateEvt evt) {
        log.debug("update inquiry result saga start,id:{}", evt.getId());
        transaction = genCreateIstrTransaction(evt);
        transaction.start();
    }

    public ITransaction genCreateIstrTransaction(PRInquiryResultUpdateEvt evt) {
        prInquiryResultUpdateEvt = evt;
        return new ParallelTransaction(Arrays.asList(new PRInqResultUpdateTransaction(evt)));
    }

    @Override
    protected void checkIsFinish() {
        DomainCommand command = createCmd(transaction.getStatus());
        if (command != null) {
            CommandGatewayFactory.getCommandGateway().send(transaction.fillCmd(command));
            log.debug("saga send：{}", command);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", prInquiryResultUpdateEvt.getId());
        }

    }

    private DomainCommand createCmd(Status status) {
        if (status == Status.SUCCEED) {
            PRUpdateConfirmInquiryResultCmd cmd = new PRUpdateConfirmInquiryResultCmd();
            cmd.setId(prInquiryResultUpdateEvt.getId());
            cmd.setRequestId(prInquiryResultUpdateEvt.getRequestId());
            BeanUtils.copyPropertiesIgnoreNull(prInquiryResultUpdateEvt, cmd);
            return cmd;
        } else if (status == Status.FAILED) {
            PRUpdateFailedInquiryResultCmd cmd = new PRUpdateFailedInquiryResultCmd();
            cmd.setId(prInquiryResultUpdateEvt.getId());
            cmd.setRequestId(prInquiryResultUpdateEvt.getRequestId());
            return cmd;
        } else {
            return null;
        }
    }



}
