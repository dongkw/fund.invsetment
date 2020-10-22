package fund.investment.trade.domain.model.eventhandler.saga.create;

import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;

/**
 * @Author dongkw
 * @Date 2020/9/18、5:28 下午
 **/
public interface IStatusHandler {

    void handler(OrderValueObject orderValueObject);
}
