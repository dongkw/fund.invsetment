package fund.investment.trade.domain.model.eventhandler.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:25 下午
 **/
public class OrderSaga {

    public final transient CommandGateway commandGateway;

    @Autowired
    public OrderSaga(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
}
