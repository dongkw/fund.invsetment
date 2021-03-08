package fund.investment.app.pledge.repo.server.service.trade.saga;

import fund.investment.app.pledge.repo.api.event.trade.PROrderMatchEvt;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.basic.trade.api.command.MatchOrderConfirmCmd;
import fund.investment.gateway.api.compliance.command.order.MatchOrderCmplCmd;
import fund.investment.gateway.api.compliance.event.order.OrderCmplMatchEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2021/3/8、5:01 下午
 **/
@Saga
@Slf4j
public class PRMatchSage {

    @Autowired
    private transient CommandGateway commandGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void startSaga(PROrderMatchEvt evt) {
        log.info("start saga by {}", evt);
        MatchOrderCmplCmd<PledgeTradeElement> cmd = new MatchOrderCmplCmd<>();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCmplMatchEvt<PledgeTradeElement> evt) {
        log.info("confirm match by {}", evt);
        MatchOrderConfirmCmd cmd = new MatchOrderConfirmCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }
}
