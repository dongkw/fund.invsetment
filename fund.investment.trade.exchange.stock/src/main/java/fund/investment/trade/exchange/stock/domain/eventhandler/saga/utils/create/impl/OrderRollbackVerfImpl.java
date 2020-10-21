package fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.impl;

import fund.investment.infrastructure.book.domain.model.command.order.RollbackVerfOrderCmd;
import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.HandlerFactory;
import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.IStatusHandler;
import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.vo.OrderSagaStatus;
import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.vo.OrderVo;
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
    @Autowired
    private CommandGateway commandGateway;

    public OrderRollbackVerfImpl() {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_FAIL), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_FAIL), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_SUCC), this);
    }

    @Override
    public void handler(OrderVo vo) {

        RollbackVerfOrderCmd cmd = new RollbackVerfOrderCmd(vo.getUnitId(), vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);

    }
}
