package fund.investment.app.pledge.repo.server.service.trade.saga;

import fund.investment.app.pledge.repo.api.event.trade.PROrderUpdateEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.UpdateOrderConfirmCmd;
import fund.investment.gateway.api.compliance.command.order.UpdateOrderCmplCmd;
import fund.investment.gateway.api.compliance.event.order.OrderCmplUpdateEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;


@Saga
@Slf4j
public class PRUpdateOrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void startSaga(PROrderUpdateEvt evt) {
        UpdateOrderCmplCmd<PledgeTradeElement> cmd = new UpdateOrderCmplCmd<>();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);

    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCmplUpdateEvt<PledgeTradeElement> evt) {
        log.info("Recieved Event: {}", evt);
        UpdateOrderConfirmCmd<PledgeTradeElement> cmd = new UpdateOrderConfirmCmd<>();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
        log.info("Send command: {}", cmd);
    }


}
