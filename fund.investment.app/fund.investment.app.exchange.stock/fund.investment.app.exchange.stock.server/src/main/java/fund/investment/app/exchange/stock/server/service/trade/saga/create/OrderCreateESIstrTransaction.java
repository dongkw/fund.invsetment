package fund.investment.app.exchange.stock.server.service.trade.saga.create;


import fund.investment.app.exchange.stock.api.command.instruction.ESCancelIstrOrderCmd;
import fund.investment.app.exchange.stock.api.command.instruction.ESCreateIstrOrderCmd;
import fund.investment.app.exchange.stock.api.valueobject.ExchangeStockIstrOrderTradeElement;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.instruction.api.enumeration.TradeSide;
import fund.investment.basic.instruction.api.event.IstrOrderCancelledEvt;
import fund.investment.basic.instruction.api.event.IstrOrderCreatedEvt;
import fund.investment.basic.instruction.api.event.IstrOrderFailedEvt;
import fund.investment.basic.trade.server.saga.OrderVo;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class OrderCreateESIstrTransaction extends TransactionUnit {

    private OrderVo vo;


    public OrderCreateESIstrTransaction(OrderVo orderVo) {
        this.vo = orderVo;
        eventList.add(IstrOrderCancelledEvt.class);
        eventList.add(IstrOrderFailedEvt.class);
        eventList.add(IstrOrderCreatedEvt.class);
    }

    public void start() {
        ESCreateIstrOrderCmd createIstrOrderCmd = new ESCreateIstrOrderCmd();
        createIstrOrderCmd.setId(vo.getIstrId());
        createIstrOrderCmd.setOrderId(vo.getOrderId());
        createIstrOrderCmd.setTradeType(vo.getTradeType());
        ExchangeStockIstrOrderTradeElement exchangeStockOrderTradeElement = new ExchangeStockIstrOrderTradeElement(vo.getTradeType().name(), vo.getSecurityCode(), vo.getQuantity(), vo.getPrice() + "", TradeSide.BUY, vo.getAmount());
        createIstrOrderCmd.setExchangeStockIstrOrderTradeElement(exchangeStockOrderTradeElement);
        CommandGatewayFactory.getCommandGateway().send(createIstrOrderCmd);
        log.debug("saga send:{}", createIstrOrderCmd);
    }

    public void rollback() {
        ESCancelIstrOrderCmd cmd = new ESCancelIstrOrderCmd();
        cmd.setId(vo.getIstrId());
        cmd.setOrderId(vo.getOrderId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
}

    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(IstrOrderCreatedEvt.class)) {
            this.status = Status.SUCCEED;
        } else if (event.getClass().isAssignableFrom(IstrOrderFailedEvt.class)) {
            this.status = Status.FAILED;
        } else if (event.getClass().isAssignableFrom(IstrOrderCancelledEvt.class)) {
            this.status = Status.FAILED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }


}
