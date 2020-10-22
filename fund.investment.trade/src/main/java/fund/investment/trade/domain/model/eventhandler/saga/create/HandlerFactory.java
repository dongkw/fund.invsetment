package fund.investment.trade.domain.model.eventhandler.saga.create;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderSagaStatus;
import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderVo;

import java.util.*;

@Component
@Slf4j
public class HandlerFactory {

    private static final Map<String, Set<IStatusHandler>> handlerMap = new HashMap<>();

    public static void register(List<OrderSagaStatus> statuses, IStatusHandler handler) {
        Set<IStatusHandler> set = handlerMap.getOrDefault(list2str(statuses), new HashSet<>());
        set.add(handler);
        handlerMap.put(list2str(statuses), set);
    }

    public void handler(OrderVo vo) {
        log.debug("now orderId,{} status:{},", vo.getOrderId(), vo.getStatuses());
        handlerMap.getOrDefault(set2str(vo.getStatuses()), new HashSet<>()).forEach(t -> t.handler(vo));
    }

    private static String list2str(List<OrderSagaStatus> statuses) {
        return statuses.stream().map(Objects::toString).sorted().reduce("", (start, t) -> start + "-" + t);
    }

    private static String set2str(Set<OrderSagaStatus> statuses) {
        return statuses.stream().map(Objects::toString).sorted().reduce("", (start, t) -> start + "-" + t);
    }
}
