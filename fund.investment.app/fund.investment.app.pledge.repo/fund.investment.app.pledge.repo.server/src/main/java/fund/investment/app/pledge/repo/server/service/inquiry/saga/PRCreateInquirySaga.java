package fund.investment.app.pledge.repo.server.service.inquiry.saga;

import fund.investment.app.pledge.repo.api.event.inquiry.PRInquiryCreatedEvt;
import fund.investment.app.pledge.repo.server.service.inquiry.saga.create.PRInquiryCreateCmplTransaction;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.inquiry.api.command.CreateConfirmInquiryCmd;
import fund.investment.basic.inquiry.api.command.CreateFailInquiryCmd;
import fund.investment.basic.inquiry.server.saga.InquiryVo;
import fund.investment.basic.inquiry.server.saga.create.CreateInquirySaga;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/
@Saga
@Slf4j
public class PRCreateInquirySaga extends CreateInquirySaga {

    private InquiryVo vo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRInquiryCreatedEvt evt) {
        log.debug("create Inquiry saga start,id:{}", evt.getId());
        transaction = genCreateInquiryTransaction(evt);
        transaction.start();
    }

    public ITransaction genCreateInquiryTransaction(PRInquiryCreatedEvt evt) {
        vo = createInquiryVo(evt);
        return new ParallelTransaction(Arrays.asList(new PRInquiryCreateCmplTransaction(evt)));
    }

    private InquiryVo createInquiryVo(PRInquiryCreatedEvt evt) {
        InquiryVo InquiryVo = new InquiryVo();
        InquiryVo.setInquiryId(evt.getId());
        InquiryVo.setUnitId("A01");
        InquiryVo.setAmount(new BigDecimal(10));
        InquiryVo.setRequestId(evt.getRequestId());
        return InquiryVo;
    }
    @Override
    protected void checkIsFinish() {
        DomainCommand command = createCmd(transaction.getStatus());
        if (command != null) {
            CommandGatewayFactory.getCommandGateway().send(transaction.fillCmd(command));
            log.debug("saga send：{}", command);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", vo.getInquiryId());
        }

    }

    private DomainCommand createCmd(Status status) {
        if (status == Status.FAILED) {
            CreateFailInquiryCmd cmd = new CreateFailInquiryCmd();
            cmd.setId(vo.getInquiryId());
            cmd.setRequestId(vo.getRequestId());
            return cmd;
        } else if (status == Status.SUCCEED) {
            CreateConfirmInquiryCmd cmd = new CreateConfirmInquiryCmd();
            cmd.setId(vo.getInquiryId());
            cmd.setRequestId(vo.getRequestId());
            return cmd;
        } else {
            return null;
        }
    }

}
