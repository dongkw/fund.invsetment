package fund.investment.app.pledge.repo.server.service.trade.saga;


import fund.investment.app.pledge.repo.api.event.trade.PROrderFillEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.trade.api.command.FillOrderConfirmCmd;
import fund.investment.gateway.api.compliance.command.order.FillOrderCmplCmd;
import fund.investment.gateway.api.compliance.event.order.OrderCmplFillEvt;
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
public class PRFillOrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void startSaga(PROrderFillEvt evt) {
        log.info("start saga by {}", evt);
        FillOrderCmplCmd<PledgeTradeElement> cmd = new FillOrderCmplCmd<>();
        org.springframework.beans.BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCmplFillEvt<PledgeTradeElement> evt) {
        log.info("confirm fill by {}", evt);
        FillOrderConfirmCmd cmd = new FillOrderConfirmCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }
}
