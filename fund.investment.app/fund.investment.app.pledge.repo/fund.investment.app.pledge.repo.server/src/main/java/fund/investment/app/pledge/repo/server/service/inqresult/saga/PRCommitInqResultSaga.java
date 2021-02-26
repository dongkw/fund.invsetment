package fund.investment.app.pledge.repo.server.service.inqresult.saga;

import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultCommitEvt;
import fund.investment.app.pledge.repo.server.service.inqresult.saga.commit.PRInqResultCommitTransaction;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.inqresult.api.command.CreateConfirmInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.CreateFailInquiryResultCmd;
import fund.investment.basic.inqresult.server.saga.create.CreateInqResultSaga;
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
public class PRCommitInqResultSaga extends CreateInqResultSaga {

    private PRInquiryResultCommitEvt vo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRInquiryResultCommitEvt evt) {
        log.debug("commit InqResult saga start,id:{}", evt.getId());
        transaction = genCreateInquiryTransaction(evt);
        transaction.start();
    }

    public ITransaction genCreateInquiryTransaction(PRInquiryResultCommitEvt evt) {
        this.vo = evt;
        return new ParallelTransaction(Arrays.asList(new PRInqResultCommitTransaction(evt)));
    }

    @Override
    protected void checkIsFinish() {
        DomainCommand command = createCmd(transaction.getStatus());
        if (command != null) {
            CommandGatewayFactory.getCommandGateway().send(transaction.fillCmd(command));
            log.debug("saga send：{}", command);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", vo.getId());
        }

    }

    private DomainCommand createCmd(Status status) {
        if (status == Status.FAILED) {
            CreateFailInquiryResultCmd cmd = new CreateFailInquiryResultCmd();
            cmd.setId(vo.getId());
            cmd.setRequestId(vo.getRequestId());
            return cmd;
        } else if (status == Status.SUCCEED) {
            CreateConfirmInquiryResultCmd cmd = new CreateConfirmInquiryResultCmd();
            cmd.setId(vo.getId());
            cmd.setRequestId(vo.getRequestId());
            return cmd;
        } else {
            return null;
        }
    }


}
