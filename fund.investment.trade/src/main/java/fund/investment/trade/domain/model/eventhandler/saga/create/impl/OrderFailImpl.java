package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import infrastructure.trade.domain.model.command.FailOrderCmd;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class OrderFailImpl implements IStatusHandler {

    private final CommandGateway commandGateway;

    @Autowired
    public OrderFailImpl(CommandGateway commandGateway) {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.ISTR_FAIL, OrderSagaStatus.VERF_FAIL), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.ISTR_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.VERF_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.ISTR_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.ISTR_FAIL, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.CMPL_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.ISTR_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.CMPL_ROLLBACK, OrderSagaStatus.ISTR_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.ISTR_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.CMPL_ROLLBACK, OrderSagaStatus.VERF_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.ISTR_SUCC, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_ROLLBACK, OrderSagaStatus.VERF_ROLLBACK), this);
        this.commandGateway = commandGateway;
    }

    @Override
    public void handler(OrderValueObject vo) {
        FailOrderCmd cmd = new FailOrderCmd(vo.getOrderId(), vo.getIstrId(), null, vo.getUnitId(), null, null);
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
        SagaLifecycle.end();
        log.debug("saga end：{}", vo.getOrderId());
        log.debug("---------------------");
    }
}
