package fund.investment.basic.common.config;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventHandlerInterceptor implements MessageHandlerInterceptor<EventMessage<?>> {
    private static final Logger log = LoggerFactory.getLogger(EventHandlerInterceptor.class);

    @Override
    public Object handle(UnitOfWork<? extends EventMessage<?>> unitOfWork, InterceptorChain interceptorChain) throws Exception {
        CorelationHelper.relation(unitOfWork.getMessage());
        log.info("published a event: {}.", unitOfWork.getMessage().getPayload());
        return interceptorChain.proceed();
    }
}