package fund.investment.instruction.domain.model.eventhandler;

import fund.investment.infrastructure.instruction.domain.model.command.ReceiveIstrFillCmd;
import infrastructure.trade.domain.model.event.OrderFilledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FillHandler {
    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void on(OrderFilledEvt evt) {
        log.info("[FillHandler] Recieved Event: {}", evt);
        ReceiveIstrFillCmd receiveIstrFillCmd = new ReceiveIstrFillCmd();
        receiveIstrFillCmd.setId(evt.getInstructionId());
        receiveIstrFillCmd.setOrderId(evt.getFill().getOrderOriginalId());
        receiveIstrFillCmd.setFillId(evt.getFill().getId());
        //TODO 设置成交数量
//        receiveIstrFillCmd.setFillQuantity(10L);
        //TODO tradeType统一
//        receiveIstrFillCmd.setTradeType(evt.getTradeType());
        commandGateway.send(receiveIstrFillCmd);
        log.info("[FillHandler] Send command: {}", receiveIstrFillCmd);
    }

}

