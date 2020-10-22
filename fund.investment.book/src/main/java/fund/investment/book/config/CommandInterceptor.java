package fund.investment.book.config;

import fund.investment.infrastructure.common.DomainCommand;
import fund.investment.infrastructure.util.uid.UIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
@Component
public class CommandInterceptor implements MessageDispatchInterceptor {

    @Autowired
    private UIDGenerator uidGenerator;

    @Override
    public BiFunction<Integer, GenericCommandMessage<DomainCommand>, GenericCommandMessage<DomainCommand>> handle(List messages) {
        return (index, message) -> {
            if (message.getPayload() instanceof DomainCommand) {
                log.debug("____{},{}", message.getCommandName(), message.getMetaData());
                DomainCommand payload = message.getPayload();
            }
            log.debug("~~,{},{}", index, message);
            return message;
        };
    }
}