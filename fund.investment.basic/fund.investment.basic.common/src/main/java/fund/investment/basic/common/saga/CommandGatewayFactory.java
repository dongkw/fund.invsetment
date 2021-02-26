package fund.investment.basic.common.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandGatewayFactory {
    private static CommandGateway gateway;

    @Autowired
    private void setCommandGateway(CommandGateway commandGateway) {
        gateway = commandGateway;
    }

    public static CommandGateway getCommandGateway() {
        return gateway;
    }
}
