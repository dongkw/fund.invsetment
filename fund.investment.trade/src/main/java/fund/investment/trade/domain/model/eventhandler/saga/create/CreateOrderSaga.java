package fund.investment.trade.domain.model.eventhandler.saga.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfFailedEvt;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfRollbackedEvt;
import fund.investment.infrastructure.book.domain.model.event.order.OrderVerfSucceedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplFailedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplRollbackedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.order.OrderCmplSucceedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCancelledEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderCreatedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrOrderFailedEvt;
import fund.investment.trade.domain.model.eventhandler.saga.OrderSaga;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2020/10/10、1:54 下午
 **/
@Slf4j
public class CreateOrderSaga extends OrderSaga {

    @Getter
    @JsonProperty
    private OrderValueObject orderValueObject;

    final transient HandlerFactory factory;

    @Autowired
    public CreateOrderSaga(CommandGateway commandGateway, HandlerFactory factory) {
        super(commandGateway);
        this.factory = factory;
        this.orderValueObject = new OrderValueObject();
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplSucceedEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.CMPL_SUCC);
        factory.handler(orderValueObject);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplFailedEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.CMPL_FAIL);
        factory.handler(orderValueObject);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.CMPL_ROLLBACK);
        factory.handler(orderValueObject);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfSucceedEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.VERF_SUCC);
        factory.handler(orderValueObject);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfFailedEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.VERF_FAIL);
        factory.handler(orderValueObject);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.VERF_ROLLBACK);
        factory.handler(orderValueObject);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCreatedEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.ISTR_SUCC);
        factory.handler(orderValueObject);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderFailedEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.ISTR_FAIL);
        factory.handler(orderValueObject);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCancelledEvt evt) {
        log.debug("saga receive:{}", evt);

        orderValueObject.getStatuses().add(OrderSagaStatus.ISTR_ROLLBACK);
        factory.handler(orderValueObject);
    }
}
