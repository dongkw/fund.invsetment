package fund.investment.trade.domain.model.eventhandler.saga.create.impl;

import fund.investment.trade.domain.model.eventhandler.saga.create.IStatusHandler;
import fund.investment.trade.domain.model.eventhandler.saga.create.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author dongkw
 * @Date 2020/10/10、4:31 下午
 **/
@Slf4j
public class DefaultImpl implements IStatusHandler {

    @Override
    public void handler(OrderVo vo) {
    }

}