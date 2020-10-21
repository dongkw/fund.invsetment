package fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create;

import fund.investment.trade.exchange.stock.domain.eventhandler.saga.utils.create.vo.OrderVo;

/**
 * @Author dongkw
 * @Date 2020/9/18、5:28 下午
 **/
public interface IStatusHandler {

    void handler(OrderVo orderVo);

}
