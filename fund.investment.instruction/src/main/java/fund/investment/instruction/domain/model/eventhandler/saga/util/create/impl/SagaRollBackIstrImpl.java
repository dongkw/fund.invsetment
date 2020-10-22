package fund.investment.instruction.domain.model.eventhandler.saga.util.create.impl;

import fund.investment.infrastructure.instruction.domain.model.command.CreateFailIstrCmd;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.HandlerFactory;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.IStatusHandler;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrSagaStatus;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrVo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author dongkw
 * @Date 2020/9/18、5:28 下午
 **/
@Slf4j
@Component
public class SagaRollBackIstrImpl implements IStatusHandler {

    @Autowired
    private CommandGateway commandGateway;


    public SagaRollBackIstrImpl() {
        HandlerFactory.register(Arrays.asList(IstrSagaStatus.CMPL_FAIL, IstrSagaStatus.VERF_FAIL), this);
        HandlerFactory.register(Arrays.asList(IstrSagaStatus.CMPL_FAIL, IstrSagaStatus.VERF_SUCC, IstrSagaStatus.ROLLBACK_VREF), this);
        HandlerFactory.register(Arrays.asList(IstrSagaStatus.CMPL_SUCC, IstrSagaStatus.VERF_FAIL, IstrSagaStatus.ROLLBACK_CMPL), this);

    }

    @Override
    public void handler(IstrVo istrVo) {
        CreateFailIstrCmd cmd = new CreateFailIstrCmd(istrVo.getIstrId(),null,null,null);
        commandGateway.send(cmd);
        log.debug("saga send {}", cmd);
        SagaLifecycle.end();
        log.debug("saga end：{}", istrVo.getIstrId());
        log.debug("---------------------");
    }

}
