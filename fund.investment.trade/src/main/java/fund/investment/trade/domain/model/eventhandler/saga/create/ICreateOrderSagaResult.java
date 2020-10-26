package fund.investment.trade.domain.model.eventhandler.saga.create;

import fund.investment.trade.domain.model.eventhandler.saga.create.valueobject.OrderValueObject;

public interface ICreateOrderSagaResult {

    void success(OrderValueObject vo);

    void fail(OrderValueObject vo);

    void rollbackIstr(OrderValueObject vo);

    void rollbackCmpl(OrderValueObject vo);

    void rollbackVerf(OrderValueObject vo);

}
