package fund.investment.book.domain.eventhandler;


import fund.investment.infrastructure.book.domain.model.command.instruction.CancelVerfIstrCmd;
import fund.investment.infrastructure.book.domain.model.command.instruction.RollbackVerfIstrCmd;
import fund.investment.infrastructure.book.domain.model.command.instruction.VerfIstrCmd;
import fund.investment.infrastructure.book.domain.model.event.VerificationEvent;
import fund.investment.infrastructure.book.domain.model.event.instruction.IstrVerfCancelledEvt;
import fund.investment.infrastructure.book.domain.model.event.instruction.IstrVerfFailedEvt;
import fund.investment.infrastructure.book.domain.model.event.instruction.IstrVerfRollBackedEvt;
import fund.investment.infrastructure.book.domain.model.event.instruction.IstrVerfSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:04
 **/
@Component
@Slf4j
public class ValidIstrHandler {


    @Autowired
    private EventGateway eventGateway;

    @CommandHandler
    public void handler(VerfIstrCmd cmd) {
        log.debug("receive,{}", cmd);
        if (cmd.getId().startsWith("A")) {
            IstrVerfSucceedEvt evt = new IstrVerfSucceedEvt(cmd.getId(), cmd.getIstrId());
            eventGateway.publish(evt);
            log.debug("send,{}", evt);
        } else {
            IstrVerfFailedEvt evt = new IstrVerfFailedEvt(cmd.getId(), cmd.getIstrId());
            eventGateway.publish(evt);
            log.debug("send,{}", evt);
        }
    }

    @CommandHandler
    public void handler(CancelVerfIstrCmd cmd) {
        log.debug("receive,{}", cmd);
        VerificationEvent event = new IstrVerfCancelledEvt(cmd.getId(), cmd.getIstrId());
        eventGateway.publish(event);
        log.debug("send,{}", event);
    }

    @CommandHandler
    public void handler(RollbackVerfIstrCmd cmd) {
        log.debug("receive,{}", cmd);
        IstrVerfRollBackedEvt event = new IstrVerfRollBackedEvt(cmd.getId(), cmd.getAmount(), cmd.getIstrId());
        eventGateway.publish(event);
        log.debug("send,{}", event);

    }
}
