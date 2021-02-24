package fund.investment.trade.domain.model.eventhandler.saga2.subSaga;

import fund.investment.infrastructure.instruction.domain.model.command.CreateIstrOrderCmd;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeSide;
import fund.investment.infrastructure.instruction.domain.model.enumeration.TradeType;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;
import fund.investment.trade.domain.model.eventhandler.saga2.Factory;
import fund.investment.trade.domain.model.eventhandler.saga2.Status;
import fund.investment.trade.domain.model.eventhandler.saga2.SubSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author dongkw
 * @Date 2020/10/27、10:59 上午
 **/
@Slf4j
@Component
public class IstrSaga extends SubSaga {


    public IstrSaga() {
        Factory.register(this.getClass().getSimpleName(),this);
    }

    @Override
    public void start(OrderValueObject object) {
        log.debug("send command");

    }

    @Override
    public void rollback(OrderValueObject object) {
        log.debug("rollbcak");
    }

}
