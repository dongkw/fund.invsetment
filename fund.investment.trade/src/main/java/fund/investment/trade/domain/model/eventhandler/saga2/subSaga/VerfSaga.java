package fund.investment.trade.domain.model.eventhandler.saga2.subSaga;

import fund.investment.infrastructure.book.domain.model.command.order.VerfOrderCmd;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import fund.investment.trade.domain.model.eventhandler.saga2.Factory;
import fund.investment.trade.domain.model.eventhandler.saga2.SubSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2020/10/27、10:59 上午
 **/
@Slf4j
@Component
public class VerfSaga extends SubSaga {

    @Autowired
    private VerfRollBackSaga rollBackSaga;

    public VerfSaga() {
        Factory.register(this.getClass().getSimpleName(), this);
    }

    @Override
    public void start(OrderValueObject vo) {
        log.debug("send command");
        VerfOrderCmd verfOrderCmd = new VerfOrderCmd(vo.getUnitId(), vo.getOrderId());
        commandGateway.send(verfOrderCmd);
    }

    @Override
    public void rollback(OrderValueObject object) {
        log.debug("rollbcak");
        rollBackSaga.start(object);
    }

}
