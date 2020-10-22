package fund.investment.compliance.domain.eventhandler;

import fund.investment.infrastructure.compliance.domain.model.command.order.CancelCmplOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.command.order.CmplOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.command.order.RollBackCmplOrderCmd;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplCancelledEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplFailedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplRollbackedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2020/10/10、13:43
 **/
@Component
@Slf4j
public class ComplianceOrderHandler {


    @Autowired
    private EventGateway eventGateway;

    @CommandHandler
    public void handler(CmplOrderCmd cmd) {
        log.debug("receive :{}", cmd);
        if (cmd.getId().startsWith("A")) {
            OrderCmplSucceedEvt evt = new OrderCmplSucceedEvt(cmd.getId(), cmd.getOrderId());
            eventGateway.publish(evt);
            log.info("send:{}", evt);
        } else {
            OrderCmplFailedEvt evt = new OrderCmplFailedEvt(cmd.getId(), cmd.getOrderId());
            eventGateway.publish(evt);
            log.info("send:{}", evt);

        }
    }

    @CommandHandler
    public void handler(RollBackCmplOrderCmd cmd) {
        log.debug("receive :{}", cmd);
        OrderCmplRollbackedEvt evt = new OrderCmplRollbackedEvt(cmd.getId(), cmd.getOrderId());
        eventGateway.publish(evt);
        log.debug("send :{}", evt);

    }

    @CommandHandler
    public void handler(CancelCmplOrderCmd cmd) {
        log.debug("receive :{}", cmd);
        OrderCmplCancelledEvt evt = new OrderCmplCancelledEvt(cmd.getId(), cmd.getOrderId());
        eventGateway.publish(evt);
        log.debug("send :{}", evt);

    }

}
