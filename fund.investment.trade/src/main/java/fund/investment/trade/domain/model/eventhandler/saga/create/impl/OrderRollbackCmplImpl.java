package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import java.util.Arrays;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.infrastructure.compliance.domain.model.command.order.RollBackCmplOrderCmd;
import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderRollbackCmplImpl implements IStatusHandler {
    
	@Autowired
    private CommandGateway commandGateway;

    public OrderRollbackCmplImpl() {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_FAIL), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_SUCC), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_FAIL), this);
    }

    @Override
    public void handler(OrderVo vo) {
        RollBackCmplOrderCmd cmd = new RollBackCmplOrderCmd(vo.getUnitId(), vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
    }
}
