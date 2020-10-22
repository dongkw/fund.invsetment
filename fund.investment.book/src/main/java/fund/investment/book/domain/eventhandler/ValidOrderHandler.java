package fund.investment.book.domain.eventhandler;


import fund.investment.infrastructure.book.domain.model.command.order.CancelVerfOrderCmd;
import fund.investment.infrastructure.book.domain.model.command.order.RollbackVerfOrderCmd;
import fund.investment.infrastructure.book.domain.model.command.order.VerfOrderCmd;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfCancelledEvt;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfFailedEvt;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfRollbackedEvt;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2020/9/17、14:04
 **/
@Component
@Slf4j
public class ValidOrderHandler {

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private EventGateway eventGateway;

    @CommandHandler
    public void handler(VerfOrderCmd cmd) {
        log.debug("receive {}", cmd);
        if (cmd.getUnitId().startsWith("A")) {
            int a = 1 / 0;
            OrderVerfSucceedEvt evt = new OrderVerfSucceedEvt(cmd.getUnitId(), cmd.getOrderId());
            eventGateway.publish(evt);
            log.debug("send,{}", evt);

        } else {
            OrderVerfFailedEvt evt = new OrderVerfFailedEvt(cmd.getUnitId(), cmd.getOrderId());
            eventGateway.publish(evt);
            log.debug("send,{}", evt);

        }
    }

    @CommandHandler
    public void handler(RollbackVerfOrderCmd cmd) {
        log.debug("receive,{}", cmd);
        OrderVerfRollbackedEvt event = new OrderVerfRollbackedEvt(cmd.getUnitId(), cmd.getOrderId());
        eventGateway.publish(event);
        log.debug("send,{}", event);
    }

    @CommandHandler
    public void handler(CancelVerfOrderCmd cmd) {
        log.debug("receive,{}", cmd);
        OrderVerfCancelledEvt event = new OrderVerfCancelledEvt(cmd.getUnitId(), cmd.getOrderId());
        eventGateway.publish(event);
        log.debug("send,{}", event);
    }

}
