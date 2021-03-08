package fund.investment.app.pledge.repo.server.service.trade.saga;

import fund.investment.app.pledge.repo.api.event.trade.PROrderCancelEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.CancellingOrderCmd;
import fund.investment.basic.trade.api.event.OrderCancellingEvt;
import fund.investment.gateway.api.compliance.command.order.CancelOrderCmplCmd;
import fund.investment.gateway.api.compliance.event.order.OrderCmplCancelEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2021/3/8、1:43 下午
 **/
@Saga
@Slf4j
public class PrCancelOrderSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void startSaga(PROrderCancelEvt evt) {
        log.info("saga start by {}", evt);
        CancelOrderCmplCmd<PledgeTradeElement> cmd = new CancelOrderCmplCmd<>();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCmplCancelEvt<PledgeTradeElement> evt) {
        log.info("cancel by {}", evt);
        CancellingOrderCmd cmd = new CancellingOrderCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCancellingEvt evt) {
        log.info("canceling order {}", evt);
    }
}
