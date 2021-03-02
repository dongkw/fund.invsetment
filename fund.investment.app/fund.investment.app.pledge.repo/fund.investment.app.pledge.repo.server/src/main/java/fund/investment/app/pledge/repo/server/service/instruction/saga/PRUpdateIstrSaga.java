package fund.investment.app.pledge.repo.server.service.instruction.saga;

import fund.investment.app.pledge.repo.api.command.instruction.PRUpdateConfirmIstrCmd;
import fund.investment.app.pledge.repo.api.command.instruction.PRUpdateFailIstrCmd;
import fund.investment.app.pledge.repo.api.event.instruction.PRIstrUpdatedEvt;
import fund.investment.app.pledge.repo.server.service.instruction.saga.update.PRInstrUpdateCmplTranscationImpl;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.basic.instruction.server.service.saga.update.UpdateIstrSaga;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/
@Saga
@Slf4j
public class PRUpdateIstrSaga extends UpdateIstrSaga {

    private IstrVo istrVo;

    private PRIstrUpdatedEvt prIstrUpdatedEvt;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRIstrUpdatedEvt evt) {
        log.debug("update istr saga start,receive:{}", evt);
        transaction = genUpdateIstrTransaction(evt);
        transaction.start();
    }

    public ITransaction genUpdateIstrTransaction(PRIstrUpdatedEvt evt) {
        istrVo = createIstrVo(evt);
        prIstrUpdatedEvt = evt;
        return new ParallelTransaction(Collections.singletonList(new PRInstrUpdateCmplTranscationImpl(prIstrUpdatedEvt)));
    }

    private IstrVo createIstrVo(PRIstrUpdatedEvt evt) {
        IstrVo istrVo = new IstrVo();
        istrVo.setId(evt.getId());
//        istrVo.setUnitId(evt.getUnitId());
//        istrVo.setSecurityCode(evt.getUnitId());
        istrVo.setRequestId(evt.getRequestId());
        istrVo.setUnitId("A");
        istrVo.setSecurityCode("A");
        istrVo.setAmount(new BigDecimal(10));
        return istrVo;
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
        if (status == Status.SUCCEED) {
            PRUpdateConfirmIstrCmd cmd = new PRUpdateConfirmIstrCmd();
            BeanUtils.copyProperties(prIstrUpdatedEvt, cmd);
            cmd.setId(istrVo.getId());
            cmd.setRequestId(istrVo.getRequestId());
            return cmd;
        } else if (status == Status.FAILED) {
            PRUpdateFailIstrCmd cmd = new PRUpdateFailIstrCmd();
            cmd.setId(istrVo.getId());
            cmd.setRequestId(istrVo.getRequestId());
            return cmd;
        } else {
            return null;
        }
    }

}
