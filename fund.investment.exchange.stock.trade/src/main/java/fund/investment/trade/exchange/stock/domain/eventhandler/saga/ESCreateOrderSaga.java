package fund.investment.trade.exchange.stock.domain.eventhandler.saga;

import fund.investment.infrastructure.book.domain.model.command.order.RollbackVerfOrderCmd;
import fund.investment.infrastructure.book.domain.model.command.order.VerfOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.command.order.CmplOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.command.order.RollBackCmplOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.valueobject.ExchangeStockIstrOrderTradeElement;
import fund.investment.trade.domain.model.eventhandler.saga.create.CreateOrderSaga;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import fund.investment.trade.domain.model.eventhandler.saga.create.ICreateOrderSagaResult;
import infrastructure.trade.domain.model.command.ConfirmOrderCmd;
import infrastructure.trade.domain.model.command.FailOrderCmd;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderCreatedEvt;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.HashSet;

@Saga
@Slf4j
public class ESCreateOrderSaga extends CreateOrderSaga implements ICreateOrderSagaResult {

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


    @Override
    public void success(OrderValueObject vo) {
        ConfirmOrderCmd cmd = new ConfirmOrderCmd(vo.getOrderId(), vo.getIstrId(), null);
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);

    }

    @Override
    public void fail(OrderValueObject vo) {
        FailOrderCmd cmd = new FailOrderCmd(vo.getOrderId(), vo.getIstrId(), null, vo.getUnitId(), null, null);
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);

    }

    @Override
    public void rollbackIstr(OrderValueObject vo) {
        ESCancelIstrOrderCmd cmd = new ESCancelIstrOrderCmd();
        cmd.setId(vo.getIstrId());
        cmd.setOrderId(vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
    }

    @Override
    public void rollbackCmpl(OrderValueObject vo) {
        RollBackCmplOrderCmd cmd = new RollBackCmplOrderCmd(vo.getUnitId(), vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
    }

    @Override
    public void rollbackVerf(OrderValueObject vo) {
        RollbackVerfOrderCmd cmd = new RollbackVerfOrderCmd(vo.getUnitId(), vo.getOrderId());
        commandGateway.send(cmd);
        log.debug("saga send:{}", cmd);
    }


}
