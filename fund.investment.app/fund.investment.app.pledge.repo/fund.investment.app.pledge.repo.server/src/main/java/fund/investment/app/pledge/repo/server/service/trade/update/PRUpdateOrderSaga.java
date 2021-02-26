package fund.investment.app.pledge.repo.server.service.trade.update;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class PRUpdateOrderSaga {

    @Autowired
    private CommandGateway commandGateway;

    private boolean flag;
    private boolean staus;

//    @StartSaga
//    @SagaEventHandler(associationProperty = "id")
//    public void startSaga(PROrderModifyingEvt evt) {
//        PRModifyingOrderCmd cmd = new PRModifyingOrderCmd();
//        BeanUtils.copyProperties(evt, cmd);
//        commandGateway.send(cmd);
//
//    }

//    @SagaEventHandler(associationProperty = "id")
//    public void handler(OrderModifyingSucceedEvt evt) {
//        log.info("Recieved Event: {}", evt);
//        PRChangeModifyingOrderCmd cmd = new PRChangeModifyingOrderCmd();
//        BeanUtils.copyProperties(evt, cmd);
//        commandGateway.send(cmd);
//        log.info("Send command: {}", cmd);
//    }
//
//    @SagaEventHandler(associationProperty = "id")
//    public void handler(OrderChangeModifyingEvt evt) {
//        log.info("Recieved Event: {}", evt);
//        PRChangeModifyingOrderCmd cmd = new PRChangeModifyingOrderCmd();
//        BeanUtils.copyProperties(evt, cmd);
//        commandGateway.send(cmd);
//        log.info("Send command: {}", cmd);
//    }
//
//    @SagaEventHandler(associationProperty = "id")
//    public void handle(OrderModifyingSucceedEvt evt){
//        SagaLifecycle.end();
//    }

}
