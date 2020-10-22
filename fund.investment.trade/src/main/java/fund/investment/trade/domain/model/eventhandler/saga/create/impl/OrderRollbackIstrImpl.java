package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import java.util.Arrays;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrOrderCmd;
import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author dongkw
 * @Date 2020/10/10、4:31 下午
 **/
@Slf4j
@Component
public class OrderRollbackIstrImpl implements IStatusHandler {
	
    @Autowired
    private CommandGateway commandGateway;

    public OrderRollbackIstrImpl() {
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_SUCC), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_SUCC, OrderSagaStatus.VERF_FAIL, OrderSagaStatus.ISTR_SUCC), this);
        HandlerFactory.register(Arrays.asList(OrderSagaStatus.CMPL_FAIL, OrderSagaStatus.VERF_SUCC, OrderSagaStatus.ISTR_SUCC), this);
    }

    @Override
    public void handler(OrderVo vo) {
        ESCancelIstrOrderCmd cmd = new ESCancelIstrOrderCmd();
        cmd.setId(vo.getIstrId());
        cmd.setOrderId(vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
    }
}
