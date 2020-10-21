package fund.investment.exchange.stock.domain.model.aggregate;

import fund.investment.instruction.domain.model.aggregate.InstructionAggregate;
import fund.investment.instruction.domain.model.aggregate.status.CreatedInstructionState;
import fund.investment.instruction.domain.model.entity.IstrTradeElement;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCancelIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrCmd;
import fund.investment.instruction.exchange.stock.domain.model.command.ESCreateIstrOrderCmd;
import fund.investment.instruction.exchange.stock.domain.model.event.ESIstrCreatedEvt;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

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
    public void handle(ESCreateIstrOrderCmd cmd) {
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
        //设置聚合根交易要素相关参数
        IstrTradeElement istrTradeElement = new IstrTradeElement();
        istrTradeElement.setQuantity(evt.getQuantity());
        istrTradeElement.setSecurityCode(evt.getSecurityCode());
        istrTradeElement.setTradeType(evt.getTradeType());
        setIstrTradeElement(istrTradeElement);
        setUserId(evt.getUserId());
    }

}
