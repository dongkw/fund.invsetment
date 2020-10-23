package fund.investment.trade.exchange.stock.domain.eventhandler.saga;

import fund.investment.infrastructure.book.domain.model.command.order.VerfOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.command.order.CmplOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.valueobject.ExchangeStockIstrOrderTradeElement;
import fund.investment.trade.domain.model.eventhandler.saga.create.CreateOrderSaga;
import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderCreatedEvt;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.HashSet;

@Saga
@Slf4j
public class ESCreateOrderSaga extends CreateOrderSaga {

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESOrderCreatedEvt evt) {
        log.debug("create saga start,receive:{}", evt);
        getOrderValueObject().setOrderId(evt.getId());
        getOrderValueObject().setUnitId(evt.getUnitId());
        getOrderValueObject().setIstrId(evt.getInstructionId());
        getOrderValueObject().setStatuses(new HashSet<>());

        VerfOrderCmd verfOrderCmd = new VerfOrderCmd(getOrderValueObject().getUnitId(), getOrderValueObject().getOrderId());
        commandGateway.send(verfOrderCmd);

        ESCreateIstrOrderCmd createIstrOrderCmd = new ESCreateIstrOrderCmd();
        createIstrOrderCmd.setId(getOrderValueObject().getIstrId());
        createIstrOrderCmd.setOrderId(getOrderValueObject().getOrderId());
        createIstrOrderCmd.setTradeType(TradeType.EXCHANGE_STOCKE);

        ExchangeStockOrderTradeElement tradeElement = evt.getOrderTradeElement();
        ExchangeStockIstrOrderTradeElement exchangeStockOrderTradeElement = new ExchangeStockIstrOrderTradeElement(tradeElement.getTradeType(), tradeElement.getSecurityCode(), 0, tradeElement.getPrice().toString(), TradeSide.BUY, tradeElement.getAmount().longValue());

        createIstrOrderCmd.setExchangeStockIstrOrderTradeElement(exchangeStockOrderTradeElement);
        getOrderValueObject().setSecurityCode(tradeElement.getSecurityCode());

        commandGateway.send(createIstrOrderCmd);

        CmplOrderCmd cmplOrderCmd = new CmplOrderCmd(tradeElement.getSecurityCode(), getOrderValueObject().getOrderId());
        commandGateway.send(cmplOrderCmd);
        log.debug("saga send:{}", cmplOrderCmd);
        log.debug("saga send:{}", verfOrderCmd);
        log.debug("saga send:{}", createIstrOrderCmd);
    }
}
