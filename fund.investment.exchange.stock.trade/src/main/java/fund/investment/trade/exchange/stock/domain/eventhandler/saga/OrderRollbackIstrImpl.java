package fund.investment.trade.exchange.stock.domain.eventhandler.saga;

import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrOrderCmd;
import fund.investment.trade.domain.model.eventhandler.saga.create.impl.IRollbackIstrHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2020/10/10、4:31 下午
 **/
@Slf4j
@Component
public class OrderRollbackIstrImpl implements IRollbackIstrHandler {

    private final CommandGateway commandGateway;

    @Autowired
    public OrderRollbackIstrImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public void send(OrderValueObject vo) {
        ESCancelIstrOrderCmd cmd = new ESCancelIstrOrderCmd();
        cmd.setId(vo.getIstrId());
        cmd.setOrderId(vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
    }
}
