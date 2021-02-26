package fund.investment.app.exchange.stock.server.service.trade.saga.cancel;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.trade.server.saga.OrderVo;
import fund.investment.gateway.api.compliance.command.order.CmplOrderCmd;
import fund.investment.gateway.api.compliance.command.order.RollBackCmplOrderCmd;
import fund.investment.gateway.api.compliance.event.order.OrderCmplFailedEvt;
import fund.investment.gateway.api.compliance.event.order.OrderCmplRollbackedEvt;
import fund.investment.gateway.api.compliance.event.order.OrderCmplSucceedEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderCancelESIstrTransaction extends TransactionUnit {

    private OrderVo vo;

    public OrderCancelESIstrTransaction(OrderVo vo) {
        this.vo = vo;
        eventList.add(OrderCmplSucceedEvt.class);
        eventList.add(OrderCmplFailedEvt.class);
        eventList.add(OrderCmplRollbackedEvt.class);
    }


    @Override
    public void start() {
        CmplOrderCmd cmd = new CmplOrderCmd(vo.getSecurityCode(), vo.getOrderId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
}

    @Override
    public void rollback() {
        RollBackCmplOrderCmd cmd = new RollBackCmplOrderCmd(vo.getUnitId(), vo.getOrderId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
}

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(OrderCmplSucceedEvt.class)) {
            status = Status.SUCCEED;
        } else if (event.getClass().isAssignableFrom(OrderCmplFailedEvt.class)) {
            status = Status.FAILED;
        } else if (event.getClass().isAssignableFrom(OrderCmplRollbackedEvt.class)) {
            status = Status.FAILED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
