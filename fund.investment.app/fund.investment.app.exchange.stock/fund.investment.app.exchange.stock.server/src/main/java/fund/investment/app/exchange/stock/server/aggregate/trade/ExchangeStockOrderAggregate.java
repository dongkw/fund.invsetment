package fund.investment.app.exchange.stock.server.aggregate.trade;

import fund.investment.app.exchange.stock.api.command.trade.ESCancelOrderCmd;
import fund.investment.app.exchange.stock.api.command.trade.ESCreateOrderCmd;
import fund.investment.app.exchange.stock.api.command.trade.ESFailOrderCmd;
import fund.investment.app.exchange.stock.api.command.trade.ESFillOrderCmd;
import fund.investment.app.exchange.stock.api.event.trade.ESOrderCreatedEvt;
import fund.investment.app.exchange.stock.api.event.trade.ESOrderFilledEvt;
import fund.investment.app.exchange.stock.api.valueobject.ExchangeStockOrderTradeElement;
import fund.investment.app.exchange.stock.server.entity.trade.ExchangeStockFill;
import fund.investment.basic.trade.api.valueobject.Fill;
import fund.investment.basic.trade.server.aggregate.OrderAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger")
public class ExchangeStockOrderAggregate extends OrderAggregate {

    private List<Fill> fills = new LinkedList<Fill>();

    private BigDecimal averageFillPrice;

    private long totalFillQuantity;

    private BigDecimal totalFillAmount;

    private long totalCancelledQuantity;

    private BigDecimal clearAmount;

    private void initAggregate() {
        this.averageFillPrice = BigDecimal.ZERO;
        this.totalFillQuantity = 0;
        this.totalFillAmount = BigDecimal.ZERO;
        this.totalCancelledQuantity = 0;
        this.clearAmount = BigDecimal.ZERO;
    }

    @CommandHandler
    public ExchangeStockOrderAggregate(ESCreateOrderCmd cmd) {
        initAggregate();
        ESOrderCreatedEvt evt = new ESOrderCreatedEvt();
        evt.setId(cmd.getId());
        AggregateLifecycle.apply(evt);
    }

    @CommandHandler
    public void on(ESFailOrderCmd cmd) {
        getOrderAggrState().getOrderState().fail(cmd);
    }

    @CommandHandler
    public void on(ESCancelOrderCmd cmd) {
        getOrderAggrState().getOrderState().cancel(cmd);
    }

    @CommandHandler
    public void on(ESFillOrderCmd cmd) {
        getOrderAggrState().getOrderState().fill(cmd);
    }

    @EventSourcingHandler
    public void on(ESOrderCreatedEvt evt) {
        setId(evt.getId());
//        setOrderState(new CreatedOrderState(new NoNeedMatchOrderStateImpl()));
//        setInstructionId(evt.getInstructionId());
//        setTradeType(evt.getTradeType());
//        setUnitId(evt.getUnitId());
//        setAccountId(evt.getAccountId());
//        setUserId(evt.getUserId());
//        setTradeElement(evt.getOrderTradeElement());

        this.averageFillPrice = evt.getAverageFillPrice();
        this.totalCancelledQuantity += evt.getTotalCancelledQuantity();
        this.totalFillAmount = evt.getTotalFillAmount();
        this.clearAmount = evt.getClearAmount();
        this.totalFillQuantity = evt.getTotalFillQuantity();
    }

    @EventSourcingHandler
    public void on(ESOrderFilledEvt evt) {
//        setInstructionId(evt.getInstructionId());
//        setTradeType(evt.getTradeType());
        getFills().add(evt.getFill());
        completedIfSatisfy(evt);
        partialFilledIfSatisfy(evt);
    }

    private void completedIfSatisfy(ESOrderFilledEvt evt) {
        if (!checkSatisfy(evt))
            return;
//        setOrderState(new CompletedOrderState(getOrderState().getOrderMatchState()));
    }

    private void partialFilledIfSatisfy(ESOrderFilledEvt evt) {
        if (checkSatisfy(evt))
            return;
//        setOrderState(new PartialFilledOrderState(getOrderState().getOrderMatchState()));
    }

    private boolean checkSatisfy(ESOrderFilledEvt evt) {
        Long filledQuantity = getFills()
                .stream()
                .mapToLong(it -> {
                    ExchangeStockFill fillItem = (ExchangeStockFill) it;
                    return fillItem.getFillQuantity();
                })
                .sum();
        ExchangeStockOrderTradeElement tradeElement = new ExchangeStockOrderTradeElement();
        return filledQuantity >= tradeElement.getQuantity();
    }
}
