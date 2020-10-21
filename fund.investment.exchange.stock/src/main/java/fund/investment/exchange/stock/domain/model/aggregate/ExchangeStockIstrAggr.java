package fund.investment.exchange.stock.domain.model.aggregate;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import fund.investment.instruction.domain.model.aggregate.status.CreatedInstructionState;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrCmd;
import fund.investment.instruction.exchange.stock.domain.model.event.ESIstrCreatedEvt;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Aggregate
@NoArgsConstructor
public class ExchangeStockIstrAggr extends InstructionAggregate {

    @CommandHandler
    public ExchangeStockIstrAggr(ESCreateIstrCmd cmd) {
        log.info("[ExchangeStockIstrAggr] Receive command: {}", cmd);
        ESIstrCreatedEvt esIstrCreatedEvt = new ESIstrCreatedEvt();
        esIstrCreatedEvt.setId(cmd.getId());
        esIstrCreatedEvt.setAccountId(cmd.getAccountId());
        esIstrCreatedEvt.setQuantity(cmd.getQuantity());
        esIstrCreatedEvt.setSecurityCode(cmd.getSecurityCode());
//        esIstrCreatedEvt.setIstrTradeElement(cmd.getIstrTradeElement());
        esIstrCreatedEvt.setTradeType(cmd.getTradeType());
        esIstrCreatedEvt.setUnitId(cmd.getUnitId());
        esIstrCreatedEvt.setUserId(cmd.getUserId());
        AggregateLifecycle.apply(esIstrCreatedEvt);
        log.info("[ExchangeStockIstrAggr] Dispached event: {}", esIstrCreatedEvt);
    }

    @CommandHandler
    public void handle(ESCancelIstrCmd cmd) {
        log.info("[ExchangeStockIstrAggr] Receive command: {}", cmd);
        getInstructionState().cancel(this,cmd);
    }

    @CommandHandler
    public void handle(CreateIstrOrderCmd cmd) {
        log.info("[ExchangeStockIstrAggr] Receive command: {}", cmd);
        getInstructionState().createOrder(this, cmd);
    }

    @CommandHandler
    public void handle(ESCancelIstrOrderCmd cmd) {
        log.info("[ExchangeStockIstrAggr] Receive command: {}", cmd);
        getInstructionState().cancelOrder(cmd);
    }

    @EventSourcingHandler
    public void on(ESIstrCreatedEvt evt) {
        log.info("[ExchangeStockIstrAggr] Receive event: {}", evt);
        setId(evt.getId());
        setInstructionState(new CreatedInstructionState());
        setAccountId(evt.getAccountId());
        setUnitId(evt.getUnitId());
//        setIstrTradeElement(evt.getIstrTradeElement());
        setUserId(evt.getUserId());
    }

}
