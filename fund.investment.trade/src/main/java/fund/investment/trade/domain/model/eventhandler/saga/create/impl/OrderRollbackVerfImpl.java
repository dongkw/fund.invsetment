package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import fund.investment.infrastructure.book.domain.model.command.order.RollbackVerfOrderCmd;
import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author dongkw
 * @Date 2020/10/10、4:31 下午
 **/
@Slf4j
@Component
public class OrderRollbackVerfImpl implements IStatusHandler {

    private final CommandGateway commandGateway;

    @Autowired
    public OrderRollbackVerfImpl(CommandGateway commandGateway) {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_FAIL), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_FAIL), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_SUCC), this);
        this.commandGateway = commandGateway;
    }

    @Override
    public void handler(OrderValueObject vo) {
        RollbackVerfOrderCmd cmd = new RollbackVerfOrderCmd(vo.getUnitId(), vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
    }
}
