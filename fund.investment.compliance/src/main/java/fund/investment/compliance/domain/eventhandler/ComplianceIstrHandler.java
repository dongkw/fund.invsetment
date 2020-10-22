package fund.investment.compliance.domain.eventhandler;

import fund.investment.infrastructure.compliance.domain.model.command.instruction.CancelComplIstrCmd;
import fund.investment.infrastructure.compliance.domain.model.command.instruction.CmplIstrCmd;
import fund.investment.infrastructure.compliance.domain.model.command.instruction.RollbackCmplIstrCmd;
import fund.investment.infrastructure.compliance.domain.model.event.instruction.IstrCmplCancelledEvt;
import fund.investment.infrastructure.compliance.domain.model.event.instruction.IstrCmplFailedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.instruction.IstrCmplRollbackedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.instruction.IstrCmplSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2020/9/17、13:43
 **/
@Component
@Slf4j
public class ComplianceIstrHandler {

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private EventGateway eventGateway;

    @CommandHandler
    public void handler(CmplIstrCmd cmd) {
        log.debug("receive {}", cmd);
        if (cmd.getId().startsWith("A")) {
            IstrCmplSucceedEvt evt = new IstrCmplSucceedEvt(cmd.getId(), cmd.getIstrId());
            eventGateway.publish(evt);
            log.debug("send {}", evt);
        } else {
            IstrCmplFailedEvt evt = new IstrCmplFailedEvt(cmd.getId(), cmd.getIstrId());
            eventGateway.publish(evt);
            log.debug("send {}", evt);
        }
    }

    @CommandHandler
    public void on(CancelComplIstrCmd cmd) {
        log.info("receive {}", cmd);
        IstrCmplCancelledEvt evt = new IstrCmplCancelledEvt(cmd.getId(), cmd.getIstrId());
        eventGateway.publish(evt);
        log.debug("send {}", evt);
    }

    @CommandHandler
    public void handler(RollbackCmplIstrCmd cmd) {
        log.info("receive {}", cmd);
        IstrCmplRollbackedEvt evt = new IstrCmplRollbackedEvt(cmd.getId(), cmd.getIstrId());
        eventGateway.publish(evt);
        log.debug("send {}", evt);

    }


}
