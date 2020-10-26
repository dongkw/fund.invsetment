package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import fund.investment.trade.domain.model.eventhandler.saga.create.ICreateOrderSagaResult;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class OrderFailImpl implements IStatusHandler {

    private final CommandGateway commandGateway;

    private final ICreateOrderSagaResult creatSaga;

    @Autowired
    public OrderFailImpl(CommandGateway commandGateway, ICreateOrderSagaResult creatSaga) {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.ISTR_FAIL, OrderSagaStatus.VERF_FAIL), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.ISTR_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.VERF_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.ISTR_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.ISTR_FAIL, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.CMPL_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.ISTR_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.CMPL_ROLLBACK, OrderSagaStatus.ISTR_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.ISTR_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.CMPL_ROLLBACK, OrderSagaStatus.VERF_ROLLBACK), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.ISTR_SUCC, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_ROLLBACK, OrderSagaStatus.VERF_ROLLBACK), this);
        this.commandGateway = commandGateway;
        this.creatSaga = creatSaga;
    }

    @Override
    public void handler(OrderValueObject vo) {
        creatSaga.fail(vo);

    }
}
