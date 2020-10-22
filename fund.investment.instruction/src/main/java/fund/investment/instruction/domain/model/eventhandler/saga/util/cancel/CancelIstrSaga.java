package fund.investment.instruction.domain.model.eventhandler.saga.util.cancel;


import com.fasterxml.jackson.annotation.JsonProperty;
import fund.investment.infrastructure.book.domain.model.command.VerificationCommand;
import fund.investment.infrastructure.book.domain.model.command.instruction.CancelVerfIstrCmd;
import fund.investment.infrastructure.book.domain.model.event.instruction.IstrVerfCancelledEvt;
import fund.investment.infrastructure.compliance.domain.model.command.ComplianceCommand;
import fund.investment.infrastructure.compliance.domain.model.command.instruction.CancelComplIstrCmd;
import fund.investment.infrastructure.compliance.domain.model.event.instruction.IstrCmplCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.command.CancelConfIstrCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.OrderStatus;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCancellingEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCompletedEvt;
import fund.investment.infrastructure.instruction.domain.model.valueobject.Order;
import fund.investment.instruction.domain.model.eventhandler.saga.util.InstructionSaga;
import infrastructure.trade.domain.model.command.CancelOrderCmd;
import infrastructure.trade.domain.model.event.OrderCancelledEvt;
import infrastructure.trade.domain.model.event.OrderFilledEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Author dongkw
 * @Date 2020/9/17、13:38
 **/
@Slf4j
public class CancelIstrSaga extends InstructionSaga {

    @JsonProperty
    private boolean cancelVerf;

    @JsonProperty
    private boolean cancelCmpl;

    @JsonProperty
    private Set<String> orderIds;

    @JsonProperty
    private String istrId;

    @JsonProperty
    private String unitId;

    @JsonProperty
    private String securityCode;

    public CancelIstrSaga() {
        this.orderIds = new HashSet<>();
    }

    public void startSaga(IstrCancellingEvt evt) {
        log.debug("Cancel saga start receive:{}", evt);
        if (hasUnFinishOrder(evt)) {
            List<Order> orders = evt.getOrders();
            orders.stream().filter(t -> Objects.equals(t.getStatus(), OrderStatus.UNDEFINED)
                    || Objects.equals(t.getStatus(), OrderStatus.PART_ENTRUSTED))
                    .forEach(t -> {
                        CancelOrderCmd cancelOrderCmd = new CancelOrderCmd(evt.getId(), evt.getId(), "场内", evt.getTradeType().name(), 11L);
                        log.debug("saga send:{}", cancelOrderCmd);
                        orderIds.add(t.getId());
                        commandGateway.send(cancelOrderCmd);
                    });

        } else {
            istrId = evt.getId();
            unitId = evt.getUnitId();
            securityCode = evt.getSecurityCode();
            CancelConfIstrCmd cancelConfIstrCmd = new CancelConfIstrCmd(istrId, evt.getTradeType());
            commandGateway.send(cancelConfIstrCmd);
        }

    }

    @SagaEventHandler(associationProperty = "id")
    public void handler(OrderCancelledEvt evt) {
        orderIds.remove(evt.getId());
        log.debug("saga receive:{},orders:{}", evt, orderIds);
        if (orderIds.isEmpty()) {
            CancelConfIstrCmd cancelConfIstrCmd = new CancelConfIstrCmd(istrId, null);
            commandGateway.send(cancelConfIstrCmd);
            log.debug("saga send:{}", cancelConfIstrCmd);
        }
    }

    @SagaEventHandler(associationProperty = "instructionId", keyName = "id")
    public void handler(OrderFilledEvt evt) {
        orderIds.remove(evt.getId());
        log.debug("saga receive:{},orders:{}", evt, orderIds);
        if (orderIds.isEmpty()) {
            CancelConfIstrCmd cancelConfIstrCmd = new CancelConfIstrCmd(istrId, null);
            commandGateway.send(cancelConfIstrCmd);
            log.debug("saga send:{}", cancelConfIstrCmd);
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(IstrCompletedEvt evt) {
        log.debug("saga receive:{}", evt);
    }


    @SagaEventHandler(associationProperty = "id")
    public void handler(IstrCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        ComplianceCommand cancelComplIstrCmd = new CancelComplIstrCmd(securityCode, istrId);
        commandGateway.send(cancelComplIstrCmd);
        log.debug("saga send:{}", cancelComplIstrCmd);
        VerificationCommand cancelVerfIstrCmd = new CancelVerfIstrCmd(unitId, istrId);
        commandGateway.send(cancelVerfIstrCmd);
        log.debug("saga send:{}", cancelVerfIstrCmd);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        cancelVerf = true;
        if (cancelCmpl) {
            SagaLifecycle.end();
            log.debug("saga end：{}", istrId);
            log.debug("---------------------");
        }
    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplCancelledEvt evt) {
        log.debug("saga receive:{}", evt);
        cancelCmpl = true;
        if (cancelVerf) {
            SagaLifecycle.end();
            log.debug("saga end：{}", istrId);
            log.debug("---------------------");
        }
    }

    private boolean hasUnFinishOrder(IstrCancellingEvt evt) {
        if (Objects.isNull(evt.getOrders())) {
            return false;
        }
        if (CollectionUtils.isEmpty(evt.getOrders())) {
            return false;
        }
        if (evt.getOrders().stream().anyMatch(t -> Objects.equals(t.getStatus(), OrderStatus.UNDEFINED)
                || Objects.equals(t.getStatus(), OrderStatus.PART_ENTRUSTED))) {
            return false;
        }
        return true;
    }

}
