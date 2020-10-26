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

/**
 * @Author dongkw
 * @Date 2020/10/10、4:31 下午
 **/
@Slf4j
@Component
public class OrderRollbackIstrImpl implements IStatusHandler {

    private final CommandGateway commandGateway;

    private final ICreateOrderSagaResult creatSaga;

    @Autowired
    public OrderRollbackIstrImpl(CommandGateway commandGateway, ICreateOrderSagaResult creatSaga) {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_SUCC), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_SUCC), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_SUCC), this);
        this.commandGateway = commandGateway;
        this.creatSaga = creatSaga;
    }

    @Override
    public void handler(OrderValueObject vo) {
        creatSaga.rollbackIstr(vo);
    }
}
