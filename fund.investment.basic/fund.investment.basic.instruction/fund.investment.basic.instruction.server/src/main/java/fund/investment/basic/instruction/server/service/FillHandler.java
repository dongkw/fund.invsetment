package fund.investment.basic.instruction.server.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FillHandler {

    @Autowired
    private CommandGateway commandGateway;

//    @EventHandler
//    public void on(OrderFilledEvt evt) {
//        log.info("Recieved Event: {}", evt);
//        ReceiveIstrFillCmd receiveIstrFillCmd = new ReceiveIstrFillCmd();
//        receiveIstrFillCmd.setId(evt.getInstructionId());
////        receiveIstrFillCmd.setOrderId(evt.getFill().getOrderOriginalId());
////        receiveIstrFillCmd.setFillId(evt.getFill().getId());
//        //TODO 设置成交数量
//        receiveIstrFillCmd.setFillQuantity(0);
//        //TODO tradeType统一
////        receiveIstrFillCmd.setTradeType(evt.getTradeType());
//        commandGateway.send(receiveIstrFillCmd);
//        log.info("Send command: {}", receiveIstrFillCmd);
//    }

}

