package fund.investment.basic.common.config;


import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandHandlerInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {
    private static final Logger log = LoggerFactory.getLogger(EventDispatcherInterceptor.class);
    private int maxRetryCount;
    private Map<String, Integer> counter;
    private EventGateway eventGateway;

    public CommandHandlerInterceptor(int maxRetryCount, EventGateway eventGateway){
        this.maxRetryCount = maxRetryCount;
        this.counter = new ConcurrentHashMap<>();
        this.eventGateway = eventGateway;
    }

    @Override
    public Object handle(UnitOfWork<? extends CommandMessage<?>> unitOfWork, InterceptorChain interceptorChain) throws Exception {
        CorelationHelper.relation(unitOfWork.getMessage());
        String identifier = unitOfWork.getMessage().getIdentifier();
        int latestMaxRetryCount = counter.getOrDefault(identifier, 0);
        DomainCommand instance = (DomainCommand) unitOfWork.getMessage().getPayload();
        try {
            return interceptorChain.proceed();
        } catch (Exception e){
            if (latestMaxRetryCount >= maxRetryCount){
                ExceptionEvent exceptionEvent = new ExceptionEvent(
                        instance.getId(),
                        unitOfWork.getMessage().getPayloadType().getName(),
                        unitOfWork.getMessage().getPayload(),
                        e.getMessage());
                eventGateway.publish(exceptionEvent);
                log.info("published exceptional event: {}", exceptionEvent.toString());
                counter.remove(identifier);
            } else{
               counter.put(identifier, ++latestMaxRetryCount);
            }
            throw e;
        }
    }
}