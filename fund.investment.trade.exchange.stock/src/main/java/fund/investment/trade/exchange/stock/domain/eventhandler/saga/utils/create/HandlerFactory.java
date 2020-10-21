package fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.vo.OrderSagaStatus;
import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.vo.OrderVo;

import java.util.*;

/**
 * @Author dongkw
 * @Date 2020/9/18、5:42 下午
 **/
@Component
@Slf4j
public class HandlerFactory {

    private static final Map<Set<OrderSagaStatus>, Set<IStatusHandler>> handlerMap = new HashMap<>();

    public static void register(List<OrderSagaStatus> statuses, IStatusHandler handler) {
        Set<IStatusHandler> set = handlerMap.getOrDefault(statuses, new HashSet<>());
        set.add(handler);
        handlerMap.put(new HashSet<>(statuses), set);
    }

    public void handler(OrderVo vo) {
        log.debug("now orderId,{} status:{},", vo.getOrderId(), vo.getStatuses());
        handlerMap.getOrDefault(vo.getStatuses(), new HashSet<>()).forEach(t -> t.handler(vo));
    }

}
