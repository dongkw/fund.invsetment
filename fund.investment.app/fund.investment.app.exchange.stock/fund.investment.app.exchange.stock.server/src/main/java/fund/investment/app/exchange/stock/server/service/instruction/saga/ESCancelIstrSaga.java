package fund.investment.app.exchange.stock.server.service.instruction.saga;


import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.SerialTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.instruction.api.enumeration.OrderStatus;
import fund.investment.basic.instruction.api.event.IstrCancellingEvt;
import fund.investment.basic.instruction.api.valueobject.Order;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.basic.instruction.server.service.saga.cancel.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/

@Saga
@Slf4j
public class ESCancelIstrSaga extends CancelIstrSaga {

    private IstrVo istrVo;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(IstrCancellingEvt evt) {
        log.debug("create istr saga start,receive:{}", evt);
        transaction = genCancelTransaction(evt);
        transaction.start();
    }

    public ITransaction genCancelTransaction(IstrCancellingEvt evt) {
        log.debug("Cancel saga start receive:{}", evt);
        istrVo = createIstrVo(evt);
        ParallelTransaction parallelTransaction = new ParallelTransaction(Arrays.asList(new IstrCancelCmplTranscation(istrVo), new IstrCancelVerfTranscation(istrVo)));
        if (hasUnFinishOrder(evt)) {
            List<Order> orders = evt.getOrders();
            Map<String, Boolean> unFinishMap = orders.stream().filter(t -> Objects.equals(t.getStatus(), OrderStatus.UNDEFINED)
                    || Objects.equals(t.getStatus(), OrderStatus.PART_ENTRUSTED)).collect(Collectors.toMap(Order::getId, v -> false));
            return new SerialTransaction(Arrays.asList(new IstrOrderCancelTranscation(unFinishMap, istrVo), new IstrCancelTranscation(istrVo, evt.getCancelType(),evt.getCancelMsg()), parallelTransaction));
        } else {
            return new SerialTransaction(Arrays.asList(new IstrCancelTranscation(istrVo, evt.getCancelType(),evt.getCancelMsg()), parallelTransaction));
        }
    }

    private IstrVo createIstrVo(IstrCancellingEvt evt) {
        IstrVo istrVo = new IstrVo();
        istrVo.setIstrId(evt.getId());
        istrVo.setUnitId(evt.getUnitId());
        istrVo.setSecurityCode(evt.getSecurityCode());
        istrVo.setTradeType(evt.getTradeType());
        return istrVo;
    }


    @Override
    protected void checkIsFinish() {
        if (transaction.getStatus() == Status.SUCCEED) {
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", istrVo.getIstrId());
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
