package fund.investment.basic.common.config;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.BiFunction;

public class EventDispatcherInterceptor implements MessageDispatchInterceptor<EventMessage<?>> {
    private static final Logger log = LoggerFactory.getLogger(EventDispatcherInterceptor.class);

    @Override
    public BiFunction<Integer, EventMessage<?>, EventMessage<?>> handle(List<? extends EventMessage<?>> list) {
        return (index, event) -> {
            CorelationHelper.relation(event);
            log.info("publish a event: {}.", event.getPayload());
            return event;
        };
    }
}
