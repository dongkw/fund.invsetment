package fund.investment.basic.common.config;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.BiFunction;

public class CommandDispatcherInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger log = LoggerFactory.getLogger(CommandDispatcherInterceptor.class);

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            CorelationHelper.relation(command);
            log.info("Dispatch a command: {}.", command.getPayload());
            return command;
        };
    }
}
