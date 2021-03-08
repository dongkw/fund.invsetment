package fund.investment.app.pledge.repo.server.aggregate.trade;

import fund.investment.app.pledge.repo.api.command.trade.PRCreateOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRMatchOrderCmd;
import fund.investment.app.pledge.repo.api.command.trade.PRUpdateOrderCmd;
import fund.investment.app.pledge.repo.api.event.trade.*;
import fund.investment.app.pledge.repo.api.valueobject.trade.PledgeTradeElement;
import fund.investment.app.pledge.repo.server.aggregate.trade.status.*;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.event.OrderCounterpartyUpdateEvt;
import fund.investment.basic.trade.api.event.OrderCreateConfirmedEvt;
import fund.investment.basic.trade.api.event.OrderPlacedEvt;
import fund.investment.basic.trade.api.event.OrderPlacingEvt;
import fund.investment.basic.trade.api.valueobject.SourceType;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


@Getter
@Setter
@Slf4j
@NoArgsConstructor
//@AllArgsConstructor
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger")
public class PledgeOrderAggregate<T extends PledgeTradeElement> extends OrderAggregate<T> {


    @CommandHandler
    public PledgeOrderAggregate(PRCreateOrderCmd cmd) {
        if (cmd.getSourceType() == SourceType.THIS_SIDE) {
            PROrderCreatedEvt evt = new PROrderCreatedEvt();
            BeanUtils.copyProperties(cmd, evt);
            AggregateLifecycle.apply(evt);
        } else {
            PROrderCreateCounterpartyEvt evt = new PROrderCreateCounterpartyEvt();
            BeanUtils.copyProperties(cmd, evt);
            AggregateLifecycle.apply(evt);
        }
    }

    @EventSourcingHandler
    public void on(PROrderCreateCounterpartyEvt evt) {
        log.info("unmatch {}", evt);
        this.orderState = new PrUnMatchState<>();
    }

    @EventSourcingHandler
    public void on(OrderCreateConfirmedEvt evt) {
        log.info("confirm {}", evt);
        this.orderState = new PrCreateConfirmState<>();
    }

    @EventSourcingHandler
    public void on(OrderPlacingEvt evt) {
        log.info("placing {}", evt);
        this.orderState = new PrPlacingState<>();
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvt<T> evt) {
        log.info("placed {}", evt);
        this.orderState = new PrSendPlacedState<>();
    }

    @EventSourcingHandler
    public void on(OrderCounterpartyUpdateEvt<T> evt) {
        this.orderState = new PrReceivePlacedState<>();
    }

    @CommandHandler
    public void handler(PRUpdateOrderCmd<T> cmd) {
        this.orderState.handler(this, cmd);
    }

    @EventSourcingHandler
    public void on(PROrderUpdateEvt evt) {
        log.info("update order evt {}", evt);
        this.orderState = new PrUpdateState<>();
    }


    @EventSourcingHandler
    public void on(PROrderUpdateConfirmEvt<T> evt) {
        log.info("update confirm {}", evt);
        BeanUtils.copyPropertiesIgnoreNull(evt.getTradeElement(), this.tradeElement);
        this.orderState = new PrPlacingState<>();
    }


    @CommandHandler
    public void handler(PRMatchOrderCmd cmd) {
        getOrderState().handler(this, cmd);
    }


}
