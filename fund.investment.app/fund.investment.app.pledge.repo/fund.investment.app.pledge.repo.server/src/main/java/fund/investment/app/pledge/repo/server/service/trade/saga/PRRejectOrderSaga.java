package fund.investment.app.pledge.repo.server.service.trade.saga;


import fund.investment.app.pledge.repo.api.event.trade.PROrderRejectEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.trade.api.command.RejectOrderConfirmCmd;
import fund.investment.gateway.api.compliance.command.order.RejectOrderCmplCmd;
import fund.investment.gateway.api.compliance.event.order.OrderCmplRejectEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class PRRejectOrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void startSaga(PROrderRejectEvt evt) {
        log.info("start saga by {}", evt);
        RejectOrderCmplCmd<PledgeTradeElement> cmd = new RejectOrderCmplCmd<>();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCmplRejectEvt<PledgeTradeElement> evt) {
        log.info("confirm reject by {}", evt);
        RejectOrderConfirmCmd cmd = new RejectOrderConfirmCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }
}
