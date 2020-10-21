package fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import fund.investment.infrastructure.book.domain.model.command.order.VerfOrderCmd;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfFailedEvt;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfRollbackedEvt;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfSucceedEvt;
import fund.investment.infrastructure.compliance.domain.model.command.order.CmplOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplFailedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplRollbackedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplSucceedEvt;
import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCreatedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderFailedEvt;
import fund.investment.infrastructure.instruction.domain.model.vo.OrderTradeElement;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.vo.ExchangeStockIstrOrderTradeElement;
import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.OrderSaga;
import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.vo.OrderSagaStatus;
import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.vo.OrderVo;
import infrastructure.trade.exchange.stock.domain.model.event.ESOrderCreatedEvt;
import infrastructure.trade.exchange.stock.domain.model.valueobject.ExchangeStockOrderTradeElement;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * @Author dongkw
 * @Date 2020/10/10、1:54 下午
 **/
@Slf4j
public class CreateOrderSaga extends OrderSaga {

    @JsonProperty
    private OrderVo orderVo;
    @Autowired
    transient HandlerFactory factory;

    public CreateOrderSaga() {
        this.orderVo = new OrderVo();
    }

    public void startSaga(ESOrderCreatedEvt evt) {
        log.debug("create saga start,receive:{}", evt);
        orderVo.setOrderId(evt.getId());
        orderVo.setUnitId(evt.getUnitId());
        orderVo.setIstrId(evt.getInstructionId());
        orderVo.setStatuses(new HashSet<>());

        VerfOrderCmd verfOrderCmd = new VerfOrderCmd(orderVo.getUnitId(), orderVo.getOrderId());
        commandGateway.send(verfOrderCmd);

        ESCreateIstrOrderCmd createIstrOrderCmd = new ESCreateIstrOrderCmd();
        createIstrOrderCmd.setId(orderVo.getIstrId());
        createIstrOrderCmd.setOrderId(orderVo.getOrderId());
        createIstrOrderCmd.setTradeType(TradeType.EXCHANGE_STOCKE);
        
        ExchangeStockOrderTradeElement tradeElement = evt.getOrderTradeElement();

        ExchangeStockIstrOrderTradeElement exchangeStockOrderTradeElement=new ExchangeStockIstrOrderTradeElement();
        exchangeStockOrderTradeElement.setPrice(tradeElement.getPrice().toString());
        exchangeStockOrderTradeElement.setAmount(tradeElement.getAmount().longValue());
        exchangeStockOrderTradeElement.setSide(TradeSide.BUY);
        exchangeStockOrderTradeElement.setSecurityCode(tradeElement.getSecurityCode());
        exchangeStockOrderTradeElement.setTradeType(tradeElement.getTradeType());

        createIstrOrderCmd.setOrderTradeElement(exchangeStockOrderTradeElement);
        orderVo.setSecurityCode(tradeElement.getSecurityCode());

        commandGateway.send(createIstrOrderCmd);

        CmplOrderCmd cmplOrderCmd = new CmplOrderCmd(tradeElement.getSecurityCode(), orderVo.getOrderId());
        commandGateway.send(cmplOrderCmd);
        log.debug("saga send:{}", cmplOrderCmd);
        log.debug("saga send:{}", verfOrderCmd);
        log.debug("saga send:{}", createIstrOrderCmd);
    }


    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.CMPL_SUCC);
        factory.handler(orderVo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.CMPL_FAIL);
        factory.handler(orderVo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.CMPL_ROLLBACK);
        factory.handler(orderVo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.VERF_SUCC);
        factory.handler(orderVo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.VERF_FAIL);
        factory.handler(orderVo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.VERF_ROLLBACK);
        factory.handler(orderVo);
    }


    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCreatedEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.ISTR_SUCC);
        factory.handler(orderVo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.ISTR_FAIL);
        factory.handler(orderVo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        orderVo.getStatuses().add(OrderSagaStatus.ISTR_ROLLBACK);
        factory.handler(orderVo);
    }


}
