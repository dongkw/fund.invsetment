package fund.investment.basic.trade.server.aggregate;

import fund.investment.basic.common.DomainAggregate;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.trade.api.command.*;
import fund.investment.basic.trade.api.event.*;
import fund.investment.basic.trade.api.valueobject.SourceType;
import fund.investment.basic.trade.api.valueobject.TradeElement;
import fund.investment.basic.trade.server.aggregate.status.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class OrderAggregate<T extends TradeElement> extends DomainAggregate {

    protected OrderState<T> orderState;

    protected T tradeElement;

    @CommandHandler
    public OrderAggregate(CreateOrderCmd<T> cmd) {
        if (cmd.getSourceType() == SourceType.THIS_SIDE) {
            OrderCreatedEvt<T> evt = new OrderCreatedEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            AggregateLifecycle.apply(evt);
        } else {
            OrderCreateCounterpartyEvt<T> evt = new OrderCreateCounterpartyEvt<>();
            BeanUtils.copyProperties(cmd, evt);
            AggregateLifecycle.apply(evt);
        }
    }

    //创建
    @CommandHandler
    public void handler(CreateOrderConfirmCmd<T> cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(CreateOrderFailedCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(PlacingOrderCmd<T> cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(PlacedOrderCmd<T> cmd) {
        getOrderState().handler(this, cmd);
    }


    //撤销
    @CommandHandler
    public void handler(CancelOrderCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(CancellingOrderCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(CancelledOrderCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(CancelOrderFailCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    //修改
    @CommandHandler
    public void handler(UpdateOrderCmd<T> cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(UpdateOrderConfirmCmd<T> cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(UpdateOrderFailCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    //成交
    @CommandHandler
    public void handler(FillOrderCmd<T> cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(FillOrderConfirmCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    //拒绝
    @CommandHandler
    public void handler(RejectOrderCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(RejectOrderConfirmCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    //匹配
    @CommandHandler
    public void handler(MatchOrderCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(MatchOrderConfirmCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(MatchOrderCancelCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(CounterpartyUpdateCmd<T> cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(AutoMatchOrderCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    @CommandHandler
    public void handler(CounterpartyFillOrdeCmd cmd) {
        getOrderState().handler(this, cmd);
    }
    @CommandHandler
    public void handler(CounterpartyRejectOrderCmd cmd) {
        getOrderState().handler(this, cmd);
    }

    //创建
    @EventSourcingHandler
    public void on(OrderCreatedEvt<T> evt) {
        BeanUtils.copyProperties(evt, this);
        this.orderState = new CreateState<>();
    }

    @EventSourcingHandler
    public void on(OrderCreateConfirmedEvt evt) {
        this.orderState = new CreateConfirmState<>();
    }

    @EventSourcingHandler
    public void on(OrderCreateCounterpartyEvt<T> evt) {
        BeanUtils.copyProperties(evt, this);
        this.orderState = new UnMatchState<>();
    }

    @EventSourcingHandler
    public void on(OrderPlacingEvt evt) {
        this.orderState = new PlacingState<>();
    }

    @EventSourcingHandler
    public void on(OrderCancelEvt evt) {
        log.info("cancel {}", evt);
        this.orderState = new CancelState<>();
    }

    @EventSourcingHandler
    public void on(OrderUpdateEvt<T> evt) {
        this.orderState = new UpdateState<>();
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvt<T> evt) {
        log.info("placed {}", evt);
        this.orderState = new SendPlacedState<>();
    }

    @EventSourcingHandler
    public void on(OrderUpdateConfirmEvt<T> evt) {
        log.info("update confirm {}", evt);
        BeanUtils.copyPropertiesIgnoreNull(evt.getTradeElement(), this.tradeElement);
        this.orderState = new PlacingState<>();
    }

    @EventSourcingHandler
    public void on(OrderCancellingEvt evt) {
        log.info("cancelling {}", evt);
        this.orderState = new CancellingState<>();
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvt evt) {
        log.info("cancelled {}", evt);
        this.orderState = new CancelledState<>();
    }

    @EventSourcingHandler
    public void on(OrderFailedEvt evt) {
        this.orderState = new FailState<>();
    }

    @EventSourcingHandler
    public void on(OrderMatchEvt evt) {
        this.orderState = new MatchState<>();
    }

    @EventSourcingHandler
    public void on(OrderMatchConfirmEvt evt) {
        this.orderState = new MatchConfirmState<>();
    }

    @EventSourcingHandler
    public void on(OrderRejectConfirmedEvt evt) {
        log.info("Reject {}", evt);
        this.orderState = new RejectConfirmState<>();
    }

    @EventSourcingHandler
    public void on(OrderFillConfirmEvt evt) {
        log.info("Fill {}", evt);
        this.orderState = new FillConfirmState<>();
    }


    @EventSourcingHandler
    public void on(OrderCounterpartyUpdateEvt<T> evt) {
        this.orderState = new ReceivePlacedState<>();
    }

    @EventSourcingHandler
    public void on(OrderRejectEvt evt) {
        this.orderState = new RejectState<>();
    }

    @EventSourcingHandler
    public void on(OrderFillEvt evt) {
        log.info("fill {}", evt);
        this.orderState = new FillState<>();
    }

    @EventSourcingHandler
    public void on(OrderCancelMatchEvt evt) {
        this.orderState = new UnMatchState<>();
    }

}
