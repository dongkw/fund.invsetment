package fund.investment.app.pledge.repo.server.service.trade.saga;


import fund.investment.basic.trade.api.command.PRMatchOrderCmd;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class ManualMatchSaga {

    @Autowired
    private CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(ManualMatchEvt evt) {
        log.debug("{}", evt);
        PRMatchOrderCmd cmd = new PRMatchOrderCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }



}
