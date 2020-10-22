package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import infrastructure.trade.domain.model.command.ConfirmOrderCmd;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class OrderSuccImpl implements IStatusHandler {

    private final CommandGateway commandGateway;

    @Autowired
    public OrderSuccImpl(CommandGateway commandGateway) {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_SUCC), this);
        this.commandGateway = commandGateway;
    }

    @Override
    public void handler(OrderValueObject vo) {
        ConfirmOrderCmd cmd = new ConfirmOrderCmd(vo.getOrderId(), vo.getIstrId(), null);
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
        SagaLifecycle.end();
        log.debug("saga end：{}", vo.getOrderId());
        log.debug("---------------------");
    }
}
