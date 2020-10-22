package fund.investment.book.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.messaging.ExecutionException;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.axonframework.messaging.unitofwork.CurrentUnitOfWork;
import org.axonframework.messaging.unitofwork.ExecutionResult;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @Author dongkw
 * @Date 2020/10/22、11:14 上午
 **/
@Slf4j
public class CustomMessage implements MessageDispatchInterceptor<EventMessage<?>> {

    @Override
    public EventMessage<?> handle(EventMessage<?> message) {
        return message;
    }

    public BiFunction<Integer, EventMessage<?>, EventMessage<?>> handle(List<? extends EventMessage<?>> messages) {
        StringBuilder sb = new StringBuilder(String.format("Events published: [%s]", messages.stream().map((m) -> {
            return m.getPayloadType().getSimpleName();
        }).collect(Collectors.joining(", "))));
        CurrentUnitOfWork.ifStarted((unitOfWork) -> {
            Message<?> message = unitOfWork.getMessage();
            if (message == null) {
                sb.append(" while processing an operation not tied to an incoming message");
            } else {
                sb.append(String.format(" while processing a [%s]", message.getPayloadType().getSimpleName()));
            }

            ExecutionResult executionResult = unitOfWork.getExecutionResult();
            if (executionResult != null) {
                if (executionResult.isExceptionResult()) {
                    Throwable exception = executionResult.getExceptionResult();
                    exception = exception instanceof ExecutionException ? exception.getCause() : exception;
                    sb.append(String.format(" which failed with a [%s]", exception.getClass().getSimpleName()));
                } else if (executionResult.getResult() != null) {
                    sb.append(String.format(" which yielded a [%s] return value", executionResult.getResult().getClass().getSimpleName()));
                }
            }
        });
        log.info(sb.toString());
        return (i, m) -> {
            return m;
        };
    }
}
