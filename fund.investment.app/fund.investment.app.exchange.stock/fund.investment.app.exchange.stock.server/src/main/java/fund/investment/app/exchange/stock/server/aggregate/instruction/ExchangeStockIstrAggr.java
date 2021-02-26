package fund.investment.app.exchange.stock.server.aggregate.instruction;

import fund.investment.app.exchange.stock.api.command.instruction.ESCancelIstrCmd;
import fund.investment.app.exchange.stock.api.command.instruction.ESCancelIstrOrderCmd;
import fund.investment.app.exchange.stock.api.command.instruction.ESCreateIstrCmd;
import fund.investment.app.exchange.stock.api.command.instruction.ESCreateIstrOrderCmd;
import fund.investment.app.exchange.stock.api.event.instruction.ESIstrCancellingEvt;
import fund.investment.app.exchange.stock.api.event.instruction.ESIstrCreatedEvt;
import fund.investment.basic.instruction.api.event.IstrCancellingEvt;
import fund.investment.basic.instruction.server.aggregate.InstructionAggregate;
import fund.investment.basic.instruction.server.aggregate.status.CancellingInstructionState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Data
@Slf4j
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger")
@NoArgsConstructor
public class ExchangeStockIstrAggr extends InstructionAggregate {

    @CommandHandler
    public ExchangeStockIstrAggr(ESCreateIstrCmd cmd) {
        ESIstrCreatedEvt esIstrCreatedEvt = new ESIstrCreatedEvt();
        esIstrCreatedEvt.setId(cmd.getId());
//        esIstrCreatedEvt.setAccountId(cmd.getAccountId());
//        esIstrCreatedEvt.setQuantity(cmd.getQuantity());
//        esIstrCreatedEvt.setSecurityCode(cmd.getSecurityCode());
//        esIstrCreatedEvt.setTradeType(cmd.getTradeType());
//        esIstrCreatedEvt.setUnitId(cmd.getUnitId());
//        esIstrCreatedEvt.setUserId(cmd.getUserId());
        AggregateLifecycle.apply(esIstrCreatedEvt);
    }

    @CommandHandler
    public void handle(ESCancelIstrCmd cmd) {
        getInstructionState().cancel(this, cmd);
    }

    @CommandHandler
    public void handle(ESCreateIstrOrderCmd cmd) {
        getInstructionState().createOrder(this, cmd);
    }

    @CommandHandler
    public void handle(ESCancelIstrOrderCmd cmd) {
        getInstructionState().cancelOrder(cmd);
    }

    @EventSourcingHandler
    public void on(ESIstrCreatedEvt evt) {
        this.setId(evt.getId());
//        setInstructionState(new CreatedInstructionState());
//        setAccountId(evt.getAccountId());
//        setUnitId(evt.getUnitId());
//        IstrTradeElement istrTradeElement = new IstrTradeElement();
//        istrTradeElement.setQuantity(evt.getQuantity());
//        istrTradeElement.setSecurityCode(evt.getSecurityCode());
//        istrTradeElement.setTradeType(evt.getTradeType());
//        setIstrTradeElement(istrTradeElement);
//        setUserId(evt.getUserId());
    }

    @EventSourcingHandler
    public void on(IstrCancellingEvt evt) {
//        ESIstrCancellingEvt esIstrCancellingEvt = new ESIstrCancellingEvt(evt.getTradeType(), evt.getId(),
//                                                                          evt.getSkId(), evt.getSkInstr());
        //TODO 参数调整
        ESIstrCancellingEvt esIstrCancellingEvt = new ESIstrCancellingEvt();
        esIstrCancellingEvt.setOrders(evt.getOrders());
        esIstrCancellingEvt.setUnitId(evt.getUnitId());
        esIstrCancellingEvt.setSecurityCode(evt.getSecurityCode());
        setInstructionState(new CancellingInstructionState());
        AggregateLifecycle.apply(esIstrCancellingEvt);
    }

    @EventSourcingHandler
    public void on(ESIstrCancellingEvt evt) {
        log.info("Receive event: {}", evt);
    }
}
