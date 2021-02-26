package fund.investment.basic.trade.server.saga.create;

import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.trade.server.saga.OrderVo;
import fund.investment.gateway.api.book.command.order.RollbackVerfOrderCmd;
import fund.investment.gateway.api.book.command.order.VerfOrderCmd;
import fund.investment.gateway.api.book.event.order.OrderVerfFailedEvt;
import fund.investment.gateway.api.book.event.order.OrderVerfRollbackedEvt;
import fund.investment.gateway.api.book.event.order.OrderVerfSucceedEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderCreateVerfTransaction extends TransactionUnit {

    private OrderVo vo;

    public OrderCreateVerfTransaction(OrderVo vo) {
        this.vo = vo;
        eventList.add(OrderVerfSucceedEvt.class);
        eventList.add(OrderVerfFailedEvt.class);
        eventList.add(OrderVerfRollbackedEvt.class);
    }

    @Override
    public void start() {
        VerfOrderCmd cmd = new VerfOrderCmd(vo.getUnitId(), vo.getOrderId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void rollback() {
        RollbackVerfOrderCmd cmd = new RollbackVerfOrderCmd(vo.getUnitId(), vo.getOrderId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(OrderVerfSucceedEvt.class)) {
            status = Status.SUCCEED;
        } else if (event.getClass().isAssignableFrom(OrderVerfFailedEvt.class)) {
            status = Status.FAILED;
        } else if (event.getClass().isAssignableFrom(OrderVerfRollbackedEvt.class)) {
            status = Status.FAILED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
