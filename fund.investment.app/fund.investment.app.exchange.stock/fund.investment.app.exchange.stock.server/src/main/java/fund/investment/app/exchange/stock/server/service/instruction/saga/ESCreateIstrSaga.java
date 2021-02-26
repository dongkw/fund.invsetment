package fund.investment.app.exchange.stock.server.service.instruction.saga;

import fund.investment.app.exchange.stock.api.event.instruction.ESIstrCreatedEvt;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.instruction.api.command.CreateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.CreateFailIstrCmd;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.basic.instruction.server.service.saga.create.CreateIstrSaga;
import fund.investment.basic.instruction.server.service.saga.create.IstrCreateVerflTranscation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/
@Saga
@Slf4j
public class ESCreateIstrSaga extends CreateIstrSaga {
    private IstrVo istrVo;
    @Autowired
    private transient CommandGateway commandGateway;
    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESIstrCreatedEvt evt) {
        log.debug("create istr saga start,receive:{}", evt);
        transaction = genCreateIstrTransaction(evt);
        transaction.start();
    }

    public ITransaction genCreateIstrTransaction(ESIstrCreatedEvt evt) {
        istrVo = createIstrVo(evt);
        return new ParallelTransaction(
                Arrays.asList(
//                        new IstrCreateCmplTranscation(istrVo, commandGateway),
                        new IstrCreateVerflTranscation(istrVo, commandGateway)));
    }

    private IstrVo createIstrVo(ESIstrCreatedEvt evt) {
        IstrVo istrVo = new IstrVo();
        istrVo.setIstrId(evt.getId());
        istrVo.setUnitId("A");
        istrVo.setSecurityCode("A");
        istrVo.setAmount(BigDecimal.TEN);
        return istrVo;
    }

    @Override
    protected void checkIsFinish() {
        if (transaction.getStatus() == Status.SUCCEED) {
            CreateConfirmIstrCmd cmd = new CreateConfirmIstrCmd();
            cmd.setId(istrVo.getIstrId());
            commandGateway.send(cmd);
            log.debug("saga send：{}", cmd);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", istrVo.getIstrId());
        } else if (transaction.getStatus() == Status.FAILED) {
            CreateFailIstrCmd cmd = new CreateFailIstrCmd();
            cmd.setId(istrVo.getIstrId());
            commandGateway.send(cmd);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", istrVo.getIstrId());
        }
    }
}