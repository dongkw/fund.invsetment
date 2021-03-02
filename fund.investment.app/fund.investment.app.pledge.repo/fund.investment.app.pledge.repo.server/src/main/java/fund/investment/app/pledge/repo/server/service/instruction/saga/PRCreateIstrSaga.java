package fund.investment.app.pledge.repo.server.service.instruction.saga;

import fund.investment.app.pledge.repo.api.event.instruction.PRIstrCreatedEvt;
import fund.investment.app.pledge.repo.api.valueobject.instruction.PledgeTradeElement;
import fund.investment.app.pledge.repo.server.service.instruction.saga.create.PRInstrCreateCmplTranscationImpl;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.instruction.api.command.CreateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.CreateFailIstrCmd;
import fund.investment.basic.instruction.server.service.saga.create.CreateIstrSaga;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/
@Saga
@Slf4j
public class PRCreateIstrSaga extends CreateIstrSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    private PRIstrCreatedEvt<PledgeTradeElement> istrVo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRIstrCreatedEvt<PledgeTradeElement> evt) {
        log.debug("create istr saga start,receive:{}", evt);
        transaction = genCreateIstrTransaction(evt);
        transaction.start();
    }

    public ITransaction genCreateIstrTransaction(PRIstrCreatedEvt<PledgeTradeElement> evt) {
        istrVo = createInstrVo(evt);
        return new ParallelTransaction(Collections.singletonList(new PRInstrCreateCmplTranscationImpl(istrVo, commandGateway)));
    }

    private PRIstrCreatedEvt<PledgeTradeElement> createInstrVo(PRIstrCreatedEvt<PledgeTradeElement> evt) {
        return evt;
    }


    @Override
    protected void checkIsFinish() {
        DomainCommand command = createCmd(transaction.getStatus());
        if (command != null) {
            CommandGatewayFactory.getCommandGateway().send(transaction.fillCmd(command));
            log.debug("saga send：{}", command);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", istrVo.getId());
        }

    }

    private DomainCommand createCmd(Status status) {
        if (status == Status.FAILED) {
            CreateFailIstrCmd cmd = new CreateFailIstrCmd();
            cmd.setId(istrVo.getId());
            cmd.setRequestId(istrVo.getRequestId());
            return cmd;
        } else if (status == Status.SUCCEED) {
            CreateConfirmIstrCmd<PledgeTradeElement> cmd = new CreateConfirmIstrCmd<>();
            cmd.setId(istrVo.getId());
            cmd.setRequestId(istrVo.getRequestId());
            return cmd;
        } else {
            return null;
        }
    }


}
