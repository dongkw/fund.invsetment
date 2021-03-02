package fund.investment.basic.instruction.server.service;

import fund.investment.basic.instruction.api.command.ApproveIstrCmd;
import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.command.DistributeIstrCmd;
import fund.investment.gateway.api.approve.event.ApproveIstrEvt;
import fund.investment.gateway.api.distribution.event.DistributedIstrEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GatewayHandler {

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void on(ApproveIstrEvt evt) {
        log.info("Recieved Event: {}", evt);
        if (evt.getApproveStatus() != null) {
            ApproveIstrCmd cmd = new ApproveIstrCmd();
            cmd.copyOf(evt);
            commandGateway.send(cmd);
            log.info("Send command: {}", cmd);
        } else {
            CancelIstrCmd cmd = new CancelIstrCmd();
            cmd.copyOf(evt);
            cmd.setRiskInfos(evt.getRiskInfos());
            commandGateway.send(cmd);
            log.info("Send command: {}", cmd);
        }
    }

    @EventHandler
    public void on(DistributedIstrEvt evt) {
        log.info("Recieved Event: {}", evt);
        if (evt.getDistributeStatus() != null) {
            DistributeIstrCmd cmd = new DistributeIstrCmd();
            cmd.copyOf(evt);
            commandGateway.send(cmd);
            log.info("Send command: {}", cmd);
        } else {
            CancelIstrCmd cmd = new CancelIstrCmd();
            cmd.copyOf(evt);
            cmd.setRiskInfos(evt.getRiskInfos());
            commandGateway.send(cmd);
            log.info("Send command: {}", cmd);
        }
    }

//    @EventHandler
//    public void on(OrderFilledEvt evt) {
//        log.info("Recieved Event: {}", evt);
//        ReceiveIstrFillCmd receiveIstrFillCmd = new ReceiveIstrFillCmd();
//        receiveIstrFillCmd.copyOf(evt);
//        //TODO 设置成交数量
//        receiveIstrFillCmd.setFillQuantity(0);
//        //TODO tradeType统一
////        receiveIstrFillCmd.setTradeType(evt.getTradeType());
//        commandGateway.send(receiveIstrFillCmd);
//        log.info("Send command: {}", receiveIstrFillCmd);
//    }

}

