package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;

public class DefaultImpl implements IStatusHandler {

    @Override
    public void handler(OrderValueObject vo) {
    }
}