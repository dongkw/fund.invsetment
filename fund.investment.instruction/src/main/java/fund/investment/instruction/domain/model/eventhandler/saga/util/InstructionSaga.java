package fund.investment.instruction.domain.model.eventhandler.saga.util;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:25 下午
 **/
public class InstructionSaga{

    @Autowired
    public transient CommandGateway commandGateway;
}
