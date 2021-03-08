package fund.investment.basic.trade.server.saga.cancel;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.trade.server.saga.OrderVo;
import fund.investment.gateway.api.book.command.order.CancelVerfOrderCmd;
import fund.investment.gateway.api.book.event.order.OrderVerfCancelledEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderCancelVerfTransaction extends TransactionUnit {

    private OrderVo vo;

    public OrderCancelVerfTransaction(OrderVo vo) {
        this.vo = vo;
        eventList.add(OrderVerfCancelledEvt.class);
    }


    @Override
    public void start() {
        CancelVerfOrderCmd cmd = new CancelVerfOrderCmd();
        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void rollback() {

    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(OrderVerfCancelledEvt.class)) {
            status = Status.SUCCEED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
