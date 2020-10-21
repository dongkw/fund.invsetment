package fund.investment.instruction.domain.model.eventhandler.saga.util.create.impl;

import fund.investment.infrastructure.book.domain.model.command.instruction.RollbackVerfIstrCmd;
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
public class SagaRollBackVerfImpl implements IStatusHandler {

    @Autowired
    private CommandGateway commandGateway;

    public SagaRollBackVerfImpl() {
        HandlerFactory.register(Arrays.asList(IstrSagaStatus.CMPL_FAIL, IstrSagaStatus.VERF_SUCC), this);
    }

    @Override
    public void handler(IstrVo istrVo) {
        RollbackVerfIstrCmd rollbackVerfIstrCmd = new RollbackVerfIstrCmd(istrVo.getUnitId(), istrVo.getAmount(), istrVo.getIstrId());
        commandGateway.send(rollbackVerfIstrCmd);
        log.debug("saga send {}", rollbackVerfIstrCmd);
    }

}