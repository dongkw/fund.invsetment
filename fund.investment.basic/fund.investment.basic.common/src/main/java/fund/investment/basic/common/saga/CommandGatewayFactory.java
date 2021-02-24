package fund.investment.basic.common.saga;

import com.qunji.common.config.SpringContextUtil;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandGatewayFactory {
    static Object lock = new Object();
    private static CommandGateway commandGateway;

    @Autowired
    public CommandGatewayFactory(CommandGateway commandGateway) {
        CommandGatewayFactory.commandGateway = commandGateway;
    }

    public static CommandGateway getCommandGateway() {
        if (null == commandGateway) {
            synchronized (lock){
                commandGateway = SpringContextUtil.getBean(DefaultCommandGateway.class);
            }
        }
        return commandGateway;
    }
}
