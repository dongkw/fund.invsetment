package fund.investment.trade.domain.model.eventhandler.saga2;

import com.sun.beans.decoder.ValueObject;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author dongkw
 * @Date 2020/10/27、10:52 上午
 **/

public abstract class SubSaga {

    @Getter
    @Setter
    private Status status;

    @Autowired
    public transient CommandGateway commandGateway;

    public abstract void start(OrderValueObject object);


    public abstract void rollback(OrderValueObject object);

}
