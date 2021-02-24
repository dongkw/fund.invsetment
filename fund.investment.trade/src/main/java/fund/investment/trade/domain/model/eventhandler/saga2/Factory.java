package fund.investment.trade.domain.model.eventhandler.saga2;

import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author dongkw
 * @Date 2020/10/27、1:25 下午
 **/
@Slf4j
@Component
public class Factory {

    private static final Map<String, SubSaga> sagas = new HashMap<>();

    @Setter
    private List<String> startList;

    @Setter
    private List<String> endList;

    public Factory() {
        startList = new ArrayList<>();
        endList = new ArrayList<>();
    }

    public static void register(String name, SubSaga sub) {
        sagas.put(name, sub);
    }

    public void setStatus(String subSaga, Status status, OrderValueObject vo) {
        sagas.get(subSaga).setStatus(status);
        sagaEnd(vo);
    }

    public void start(OrderValueObject vo) {
        log.debug("start saga:{},", vo.getOrderId());
        startList.forEach(t -> {
            SubSaga subSaga = sagas.get(t);
            subSaga.start(vo);
        });
    }

    public void rollback(OrderValueObject vo) {
        log.debug("rollback:{},", vo.getOrderId());
        startList.stream().forEach(t -> {
            SubSaga subSaga = sagas.get(t);
            if (Objects.equals(subSaga.getStatus(), Status.SUCCESS)) {
                subSaga.rollback(vo);
            }
        });
    }

    public void sagaEnd(OrderValueObject vo) {
        if (startList.stream().noneMatch(t -> Objects.isNull(sagas.get(t).getStatus()))) {
            if (startList.stream().anyMatch(t -> Objects.equals(sagas.get(t).getStatus(), Status.FAIL))) {
                rollback(vo);
            }


        }
        if (endList.stream().allMatch(t -> Objects.equals(sagas.get(t).getStatus(), Status.SUCCESS))) {
            SagaLifecycle.end();
            log.debug("---------saga end----------");
        }
    }
}
