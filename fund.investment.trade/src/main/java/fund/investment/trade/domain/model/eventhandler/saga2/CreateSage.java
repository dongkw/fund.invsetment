package fund.investment.trade.domain.model.eventhandler.saga2;

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
import fund.investment.trade.domain.model.eventhandler.saga.create.HandlerFactory;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author dongkw
 * @Date 2020/10/27、9:18 上午
 **/
@Slf4j
public class CreateSage {

    @Autowired
    transient Factory factory;

    @Setter
    @Getter
    @JsonProperty
    private OrderValueObject vo;

    public void startSaga(List<String> startList, List<String> endList, OrderValueObject vo) {
        factory.setStartList(startList);
        factory.setEndList(endList);
        factory.start(vo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        factory.setStatus("OrderSaga", Status.SUCCESS, vo);
    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        factory.setStatus("OrderSaga", Status.FAIL, vo);

    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderCmplRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);

        factory.setStatus("OrderRollBackSaga", Status.SUCCESS, vo);

    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfSucceedEvt evt) {
        log.debug("saga receive:{}", evt);

        factory.setStatus("VerfSaga", Status.SUCCESS, vo);

    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfFailedEvt evt) {
        log.debug("saga receive:{}", evt);
        factory.setStatus("VerfSaga", Status.FAIL, vo);

    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(OrderVerfRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);

        factory.setStatus("VerfRollbackSaga", Status.SUCCESS, vo);

    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCreatedEvt evt) {
        log.debug("saga receive:{}", evt);

        factory.setStatus("IstrSaga", Status.SUCCESS, vo);

    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderFailedEvt evt) {
        log.debug("saga receive:{}", evt);

        factory.setStatus("IstrSaga", Status.FAIL, vo);

    }

    @SagaEventHandler(associationProperty = "orderId", keyName = "id")
    public void handler(IstrOrderCancelledEvt evt) {
        log.debug("saga receive:{}", evt);

        factory.setStatus("IstrRollBackSaga", Status.SUCCESS, vo);

    }
}
