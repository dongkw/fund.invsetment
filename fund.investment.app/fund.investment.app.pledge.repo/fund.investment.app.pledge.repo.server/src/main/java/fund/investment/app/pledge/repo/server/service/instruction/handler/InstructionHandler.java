package fund.investment.app.pledge.repo.server.service.instruction.handler;

import fund.investment.basic.instruction.api.command.ReceiveIstrFillCmd;
import fund.investment.basic.instruction.api.event.IstrFillReceivedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InstructionHandler {

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void on(IstrFillReceivedEvt evt) {
        log.info("Recieved Event: {}", evt);
        ReceiveIstrFillCmd receiveIstrFillCmd = new ReceiveIstrFillCmd();
        receiveIstrFillCmd.setId(evt.getId());
        receiveIstrFillCmd.setChInsDealStatus(evt.getChInsDealStatus());
        receiveIstrFillCmd.setModifiedId(evt.getModifiedId());
        receiveIstrFillCmd.setModifiedTime(evt.getModifiedTime());
//        receiveIstrFillCmd.setOrderId(evt.getFill().getOrderOriginalId());
//        receiveIstrFillCmd.setFillId(evt.getFill().getId());
        //TODO 设置成交数量
        receiveIstrFillCmd.setFillQuantity(0);
        //TODO tradeType统一
//        receiveIstrFillCmd.setTradeType(evt.getTradeType());
        commandGateway.send(receiveIstrFillCmd);
        log.info("Send command: {}", receiveIstrFillCmd);
    }

//    @EventHandler
//    public void on(OrderChangedPlacingEvt evt) {
//        log.info("Recieved Event: {}", evt);
//        PRCreateIstrOrderCmd createIstrOrderCmd = new PRCreateIstrOrderCmd();
//        createIstrOrderCmd.setId(evt.getInstructionId());
//        commandGateway.send(createIstrOrderCmd);
//        log.info("Send command: {}", createIstrOrderCmd);
//    }

}

