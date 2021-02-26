package fund.investment.basic.common.config;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import org.axonframework.messaging.Message;

public final class CorelationHelper {
    public static void relation(Message<?> message){
        if(message.getPayload() instanceof DomainCommand){
            DomainCommand command = (DomainCommand) message.getPayload();
            setThreadName(command.getId());
        }else if (message.getPayload() instanceof DomainEvent){
            DomainEvent event = (DomainEvent) message.getPayload();
            setThreadName(event.getId());
        }
    }

    private static void setThreadName(String name){
        if(name != null)
            Thread.currentThread().setName(name);
    }
}
