package fund.investment.app.exchange.stock.server.service.instruction.saga;

import fund.investment.app.exchange.stock.api.event.instruction.ESIstrCreatedEvt;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.instruction.api.command.CreateConfirmIstrCmd;
import fund.investment.basic.instruction.api.command.CreateFailIstrCmd;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.basic.instruction.server.service.saga.create.CreateIstrSaga;
import fund.investment.basic.instruction.server.service.saga.update.IstrUpdateVerflTranscation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;

import java.util.Arrays;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/
//@Saga
@Slf4j
public class ESUpdateIstrSaga extends CreateIstrSaga {

    private IstrVo istrVo;
    private String status;

//    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESIstrCreatedEvt evt) {
        log.debug("update istr saga start,receive:{}", evt);
        transaction = genCreateIstrTransaction(evt);
        transaction.start();
    }

    public ITransaction genCreateIstrTransaction(ESIstrCreatedEvt evt) {
        istrVo = createIstrVo(evt);
        return new ParallelTransaction(Arrays.asList(new IstrUpdateVerflTranscation(istrVo)));
    }

    private IstrVo createIstrVo(ESIstrCreatedEvt evt) {
        IstrVo istrVo = new IstrVo();
        istrVo.setIstrId(evt.getId());
        istrVo.setUnitId(evt.getUnitId());
//        istrVo.setSecurityCode(evt.getSecurityCode());
//        istrVo.setAmount(new BigDecimal(evt.getQuantity()));
        return istrVo;
    }

    @Override
    protected void checkIsFinish() {
        if (transaction.getStatus() == Status.SUCCEED) {
            CreateConfirmIstrCmd cmd = new CreateConfirmIstrCmd();
            cmd.setId(istrVo.getIstrId());
            CommandGatewayFactory.getCommandGateway().send(cmd);
            log.debug("saga send：{}", cmd);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", istrVo.getIstrId());
        } else if (transaction.getStatus() == Status.FAILED) {
            CreateFailIstrCmd cmd = new CreateFailIstrCmd();
            cmd.setId(istrVo.getIstrId());
            CommandGatewayFactory.getCommandGateway().send(cmd);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", istrVo.getIstrId());
        }
    }


}
