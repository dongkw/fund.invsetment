package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;

/**
 * @Author dongkw
 * @Date 2020/10/22、8:37 下午
 **/
public interface IRollbackIstrHandler {
        void send(OrderValueObject valueObject);
}
