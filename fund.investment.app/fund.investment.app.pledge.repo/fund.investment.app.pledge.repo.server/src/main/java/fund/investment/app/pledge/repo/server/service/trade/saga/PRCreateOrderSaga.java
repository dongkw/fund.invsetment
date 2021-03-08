package fund.investment.app.pledge.repo.server.service.trade.saga;


import fund.investment.app.pledge.repo.api.event.trade.PROrderCreatedEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.app.pledge.repo.server.service.trade.create.OrderCreatePRIstrTransaction;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.api.event.IstrOrderCancelledEvt;
import fund.investment.basic.instruction.api.event.IstrOrderCreatedEvt;
import fund.investment.basic.instruction.api.event.IstrOrderFailedEvt;
import fund.investment.basic.trade.api.command.CreateOrderConfirmCmd;
import fund.investment.basic.trade.api.command.CreateOrderFailedCmd;
import fund.investment.basic.trade.server.saga.create.CreateOrderSaga;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.util.ArrayList;
import java.util.List;

@Saga
@Slf4j
public class PRCreateOrderSaga extends CreateOrderSaga {

    private PROrderCreatedEvt vo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PROrderCreatedEvt evt) {
        log.debug("create saga start,receive:{}", evt);
        transaction = createTransaction(evt);
        transaction.start();
        //TODO 测试时直接通过
        checkIsFinish();
    }

    private ITransaction createTransaction(PROrderCreatedEvt evt) {
        vo = CreateCreateSagaVo(evt);
        List<ITransaction> list = new ArrayList<>();
//        list.add(new OrderCreateCmplTransaction(vo));
//        list.add(new OrderCreateVerfTransaction(vo));
        list.add(new OrderCreatePRIstrTransaction(vo));
        return new ParallelTransaction(list);
    }

    private PROrderCreatedEvt CreateCreateSagaVo(PROrderCreatedEvt evt) {
        return evt;
    }

//    private OrderVo CreateCreateSagaVo(PROrderCreatedEvt evt) {
//        OrderVo orderVo = new OrderVo();
//        orderVo.setIstrId(evt.getInstructionId());
//        orderVo.setOrderId(evt.getId());
//        orderVo.setSecurityCode(evt.getOrderTradeElement().getSecurityCode());
//        orderVo.setUnitId(evt.getUnitId());
//        orderVo.setTradeType(TradeType.EXCHANGE_STOCKE);
//        ExchangeStockOrderTradeElement tradeElement = evt.getOrderTradeElement();
//        orderVo.setAmount(tradeElement.getAmount().longValue());
//        orderVo.setPrice(tradeElement.getPrice().longValue());
//        orderVo.setQuantity(tradeElement.getQuantity());
//        return orderVo;
//    }


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
            //添加请求参数
            CreateOrderConfirmCmd<PledgeTradeElement> cmd = new CreateOrderConfirmCmd<>();
            BeanUtils.copyProperties(vo, cmd);
            CommandGatewayFactory.getCommandGateway().send(cmd);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", vo.getId());

        } else if (transaction.getStatus() == Status.FAILED) {
            //添加请求参数
            CreateOrderFailedCmd cmd = new CreateOrderFailedCmd();
            BeanUtils.copyProperties(vo, cmd);
            CommandGatewayFactory.getCommandGateway().send(cmd);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", vo.getId());

        }
    }
}
