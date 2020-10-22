package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import fund.investment.infrastructure.compliance.domain.model.command.order.RollBackCmplOrderCmd;
import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class OrderRollbackCmplImpl implements IStatusHandler {

    private final CommandGateway commandGateway;

    @Autowired
    public OrderRollbackCmplImpl(CommandGateway commandGateway) {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_FAIL), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_SUCC), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_FAIL), this);
        this.commandGateway = commandGateway;
    }

    @Override
    public void handler(OrderValueObject vo) {
        RollBackCmplOrderCmd cmd = new RollBackCmplOrderCmd(vo.getUnitId(), vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
    }
}
