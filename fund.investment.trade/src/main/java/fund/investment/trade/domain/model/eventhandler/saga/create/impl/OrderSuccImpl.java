package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import java.util.Arrays;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderVo;
import infrastructure.trade.domain.model.command.ConfirmOrderCmd;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderSuccImpl implements IStatusHandler {
   
	@Autowired
    private CommandGateway commandGateway;

    public OrderSuccImpl() {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_SUCC), this);
    }

    @Override
    public void handler(OrderVo vo) {
        ConfirmOrderCmd cmd = new ConfirmOrderCmd(vo.getOrderId(), vo.getIstrId(), null);
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
        SagaLifecycle.end();
        log.debug("saga end：{}", vo.getOrderId());
        log.debug("---------------------");
    }
}
