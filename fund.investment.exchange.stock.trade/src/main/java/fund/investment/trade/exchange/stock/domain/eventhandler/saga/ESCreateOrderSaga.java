package fund.investment.trade.exchange.stock.domain.eventhandler.saga;

import java.util.HashSet;

import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import fund.investment.infrastructure.book.domain.model.command.order.VerfOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.command.order.CmplOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.valueobject.ExchangeStockIstrOrderTradeElement;
import fund.investment.trade.domain.model.eventhandler.saga.create.CreateOrderSaga;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderCreatedEvt;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
import lombok.extern.slf4j.Slf4j;

@Saga
@Slf4j
public class ESCreateOrderSaga extends CreateOrderSaga {

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESOrderCreatedEvt evt) {
            log.debug("create saga start,receive:{}", evt);
            orderVo.setOrderId(evt.getId());
            orderVo.setUnitId(evt.getUnitId());
            orderVo.setIstrId(evt.getInstructionId());
            orderVo.setStatuses(new HashSet<>());

            VerfOrderCmd verfOrderCmd = new VerfOrderCmd(null, orderVo.getUnitId(), orderVo.getOrderId());
            commandGateway.send(verfOrderCmd);

            ESCreateIstrOrderCmd createIstrOrderCmd = new ESCreateIstrOrderCmd();
            createIstrOrderCmd.setId(orderVo.getIstrId());
            createIstrOrderCmd.setOrderId(orderVo.getOrderId());
            createIstrOrderCmd.setTradeType(TradeType.EXCHANGE_STOCKE);

            ExchangeStockOrderTradeElement tradeElement = evt.getOrderTradeElement();
            ExchangeStockIstrOrderTradeElement exchangeStockOrderTradeElement=new ExchangeStockIstrOrderTradeElement(tradeElement.getTradeType(), tradeElement.getSecurityCode(), 0, tradeElement.getPrice().toString(), TradeSide.BUY, tradeElement.getAmount().longValue());

            createIstrOrderCmd.setExchangeStockIstrOrderTradeElement(exchangeStockOrderTradeElement);
            orderVo.setSecurityCode(tradeElement.getSecurityCode());

            commandGateway.send(createIstrOrderCmd);

            CmplOrderCmd cmplOrderCmd = new CmplOrderCmd(tradeElement.getSecurityCode(), orderVo.getOrderId());
            commandGateway.send(cmplOrderCmd);
            log.debug("saga send:{}", cmplOrderCmd);
            log.debug("saga send:{}", verfOrderCmd);
            log.debug("saga send:{}", createIstrOrderCmd);
        }
}
