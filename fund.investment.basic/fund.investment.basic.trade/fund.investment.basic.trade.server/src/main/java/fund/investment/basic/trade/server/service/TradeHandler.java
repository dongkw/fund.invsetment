package fund.investment.basic.trade.server.service;

import fund.investment.basic.common.util.uid.UIDGenerator;
import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.gateway.api.compliance.event.order.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2021/3/8、11:15 上午
 **/
public class TradeHandler<T extends TradeElement> {

    @Autowired
    private UIDGenerator uidGenerator;

    @Autowired
    private CommandGateway commandGateway;


    @EventHandler
    public void handler(OrderCmplPlacingEvt<T> evt) {
        PlacingOrderCmd<T> cmd = new PlacingOrderCmd<>();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EventHandler
    public void handler(OrderCmplPlacedEvt<T> evt) {
        PlacedOrderCmd<T> cmd = new PlacedOrderCmd<>();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EventHandler
    public void handler(OrderCmplFilledEvt<T> evt) {
        CounterpartyFillOrdeCmd cmd = new CounterpartyFillOrdeCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EventHandler
    public void handler(OrderCmplRejectedEvt<T> evt) {
        CounterpartyRejectOrderCmd cmd = new CounterpartyRejectOrderCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EventHandler
    public void handler(OrderCmplCancelledEvt<T> evt) {
        CancelledOrderCmd cmd = new CancelledOrderCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EventHandler
    public void handler(OrderCmplAutoMatchEvt<T> evt) {
        AutoMatchOrderCmd cmd = new AutoMatchOrderCmd();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);
    }

    @EventHandler
    public void handler(OrderCmplUpdatedEvt<T> evt) {
        CounterpartyUpdateCmd<T> cmd = new CounterpartyUpdateCmd<>();
        BeanUtils.copyProperties(evt, cmd);
        commandGateway.send(cmd);

    }
}
