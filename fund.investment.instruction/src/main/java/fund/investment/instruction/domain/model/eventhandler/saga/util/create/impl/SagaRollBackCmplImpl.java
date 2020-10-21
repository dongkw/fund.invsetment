package fund.investment.instruction.domain.model.eventhandler.saga.util.create.impl;

import fund.investment.infrastructure.compliance.domain.model.command.instruction.RollbackCmplIstrCmd;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.HandlerFactory;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.IStatusHandler;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrSagaStatus;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrVo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author dongkw
 * @Date 2020/9/18、5:28 下午
 **/
@Slf4j
@Component
public class SagaRollBackCmplImpl implements IStatusHandler {

    @Autowired
    private CommandGateway commandGateway;

    public SagaRollBackCmplImpl() {
        HandlerFactory.register(Arrays.asList(IstrSagaStatus.CMPL_SUCC, IstrSagaStatus.VERF_FAIL), this);
    }

    @Override
    public void handler(IstrVo istrVo) {

        RollbackCmplIstrCmd rollbackCmplIstrCmd = new RollbackCmplIstrCmd(istrVo.getSecurityCode(), istrVo.getIstrId());
        commandGateway.send(rollbackCmplIstrCmd);
        log.debug("saga send:{}", rollbackCmplIstrCmd);
    }

}
