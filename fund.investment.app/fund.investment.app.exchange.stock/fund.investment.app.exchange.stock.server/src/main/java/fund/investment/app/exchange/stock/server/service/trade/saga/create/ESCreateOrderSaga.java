package fund.investment.app.exchange.stock.server.service.trade.saga.create;


import fund.investment.app.exchange.stock.api.event.trade.ESOrderCreatedEvt;
import fund.investment.app.exchange.stock.api.valueobject.ExchangeStockOrderTradeElement;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import fund.investment.basic.instruction.api.event.IstrOrderCancelledEvt;
import fund.investment.basic.instruction.api.event.IstrOrderCreatedEvt;
import fund.investment.basic.instruction.api.event.IstrOrderFailedEvt;
import fund.investment.basic.trade.api.command.ConfirmOrderCmd;
import fund.investment.basic.trade.api.command.FailOrderCmd;
import fund.investment.basic.trade.server.saga.OrderVo;
import fund.investment.basic.trade.server.saga.create.CreateOrderSaga;
import fund.investment.basic.trade.server.saga.create.OrderCreateCmplTransaction;
import fund.investment.basic.trade.server.saga.create.OrderCreateVerfTransaction;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.ArrayList;
import java.util.List;

@Saga
@Slf4j
public class ESCreateOrderSaga extends CreateOrderSaga {

    private OrderVo vo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ESOrderCreatedEvt evt) {
        log.debug("create saga start,receive:{}", evt);
        transaction = createTransaction(evt);
        transaction.start();
    }

    private ITransaction createTransaction(ESOrderCreatedEvt evt) {
        vo = CreateCreateSagaVo(evt);
        List<ITransaction> list = new ArrayList<>();
        list.add(new OrderCreateCmplTransaction(vo));
        list.add(new OrderCreateVerfTransaction(vo));
        list.add(new OrderCreateESIstrTransaction(vo));
        return new ParallelTransaction(list);
    }

    private OrderVo CreateCreateSagaVo(ESOrderCreatedEvt evt) {
        OrderVo orderVo = new OrderVo();
        orderVo.setIstrId(evt.getInstructionId());
        orderVo.setOrderId(evt.getId());
        orderVo.setSecurityCode(evt.getOrderTradeElement().getSecurityCode());
        orderVo.setUnitId(evt.getUnitId());
        orderVo.setTradeType(TradeType.EXCHANGE_STOCKE);
        ExchangeStockOrderTradeElement tradeElement = evt.getOrderTradeElement();
        orderVo.setAmount(tradeElement.getAmount().longValue());
        orderVo.setPrice(tradeElement.getPrice().longValue());
        orderVo.setQuantity(tradeElement.getQuantity());
        return orderVo;
    }


    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCreatedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        doHandler(evt);
    }

    @Override
    protected void checkIsFinish() {
        if (transaction.getStatus() == Status.SUCCEED) {
            ConfirmOrderCmd cmd = new ConfirmOrderCmd(vo.getOrderId(),
                    null,
                    vo.getIstrId(),
                    null,
                    null,
                    null,
                    null);
            CommandGatewayFactory.getCommandGateway().send(cmd);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", vo.getIstrId());

        } else if (transaction.getStatus() == Status.FAILED) {
            FailOrderCmd cmd = new FailOrderCmd(vo.getOrderId(),
                    null,
                    vo.getIstrId(),
                    null,
                    vo.getUnitId(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            CommandGatewayFactory.getCommandGateway().send(cmd);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", vo.getIstrId());

        }
    }
}
