package fund.investment.basic.instruction.server.service;

import fund.investment.basic.instruction.api.command.CancelIstrCmd;
import fund.investment.basic.instruction.api.command.DistributeIstrCmd;
import fund.investment.basic.instruction.api.enumeration.EventType;
import fund.investment.gateway.api.distribution.event.DistIstrRejectedEvt;
import fund.investment.gateway.api.distribution.event.DistributedIstrEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DistributeHandler {

    @Autowired
    private CommandGateway commandGateway;

    @EventHandler
    public void on(DistributedIstrEvt evt) {
        log.info("Recieved Event: {}", evt);
        DistributeIstrCmd distributeIstrCmd = new DistributeIstrCmd();
        distributeIstrCmd.setId(evt.getId());
        distributeIstrCmd.setSkId(evt.getSkId());
        distributeIstrCmd.setSkInstr(evt.getSkInstr());

        distributeIstrCmd.setChInsDispStatus(evt.getChInsDispStatus());

        distributeIstrCmd.setChLastModifiedId(evt.getChLastModifiedId());
        distributeIstrCmd.setTsLastModifiedTime(evt.getTsLastModifiedTime());
        distributeIstrCmd.setUserId(evt.getUserId());
        commandGateway.send(distributeIstrCmd);
        log.info("Send command: {}", distributeIstrCmd);
    }

    @EventHandler
    public void on(DistIstrRejectedEvt evt) {
        log.info("Recieved Event: {}", evt);
        CancelIstrCmd cancelIstrCmd = new CancelIstrCmd();
        cancelIstrCmd.setId(evt.getInstructionId());
        cancelIstrCmd.setCancelMsg("分发拒绝");
        cancelIstrCmd.setCancelType(EventType.DISTRIBUTE_REJECT.getCode());
        cancelIstrCmd.setChLastModifiedId(evt.getOperatorId());
        cancelIstrCmd.setTsLastModifiedTime(evt.getOperatorTime());
        commandGateway.send(cancelIstrCmd);
        log.info("Send command: {}", cancelIstrCmd);
    }

}

