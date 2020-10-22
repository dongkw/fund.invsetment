package fund.investment.trade.domain.model.aggregate;

import fund.investment.infrastructure.common.DomainAggregate;
import fund.investment.trade.domain.model.aggregate.state.*;
import infrastructure.trade.domain.model.command.*;
import infrastructure.trade.domain.model.event.*;
import infrastructure.trade.domain.model.valueobject.Fill;
import infrastructure.trade.domain.model.valueobject.OrderTradeElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class OrderAggregate extends DomainAggregate {

    @AggregateIdentifier
    private String id;

    private OrderState orderState;

    private LocalDateTime orderTime;

    private String errorCode;

    private String errorMsg;

    private String exchangeId;

    private String instructionId;

    private String unitId;

    private String accountId;

    private String userId;

    private String tradeType;

    private OrderTradeElement tradeElement;

    private List<Fill> fills = new LinkedList<Fill>();

    @CommandHandler
    public void handle(ConfirmOrderCmd cmd) {
        orderState.createConfirm(cmd);
    }

    @CommandHandler
    public void handle(PlaceOrderCmd cmd) {
        orderState.placing(cmd);
    }

    @CommandHandler
    public void handle(PlaceConfirmOrderCmd cmd) {
        orderState.placed(cmd);
    }

    @CommandHandler
    public void handle(PlaceCancelOrderCmd cmd) {
        orderState.cancelling(cmd);
    }

    @CommandHandler
    public void handle(CancelConfirmOrderCmd cmd) {
        orderState.cancelConfirm(cmd);
    }

    @CommandHandler
    public void handle(FailOrderCmd cmd) {
        orderState.fail(cmd);
    }

    @EventSourcingHandler
    public void on(OrderFailedEvt evt) {
        log.info("Recieved Event: {}", evt);

        this.orderState = new FailedOrderState();
        this.instructionId = evt.getInstructionId();
        this.tradeType = evt.getTradeType();
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvt evt) {
        log.info("Recieved Event: {}", evt);

        this.orderState = new ConfirmedOrderState();
        this.instructionId = evt.getInstructionId();
        this.tradeType = evt.getTradeType();
    }

    @EventSourcingHandler
    public void on(OrderCancellingEvt evt) {
        log.info("Recieved Event: {}", evt);

        this.orderState = new CancellingOrderState();
        this.instructionId = evt.getInstructionId();
        this.tradeType = evt.getTradeType();
    }

    @EventSourcingHandler
    public void on(OrderPartialFilledCancellingEvt evt) {
        log.info("Recieved Event: {}", evt);

        this.orderState = new PFCancellingOrderState();
        this.instructionId = evt.getInstructionId();
        this.tradeType = evt.getTradeType();
    }

    @EventSourcingHandler
    public void on(OrderPlacingEvt evt) {
        log.info("Recieved Event: {}", evt);

        this.orderState = new PlacingOrderState();
        this.instructionId = evt.getInstructionId();
        this.tradeType = evt.getTradeType();
        this.exchangeId = evt.getExchangId();
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvt evt) {
        log.info("Recieved Event: {}", evt);

        this.orderState = new PlacedOrderState();
        this.instructionId = evt.getInstructionId();
        this.tradeType = evt.getTradeType();
    }

    @EventSourcingHandler
    public void on(OrderCompletedEvt evt) {
        log.info("Recieved Event: {}", evt);

        this.orderState = new CompletedOrderState();
        this.instructionId = evt.getInstructionId();
        this.tradeType = evt.getTradeType();
    }
}
