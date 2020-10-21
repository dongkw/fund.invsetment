package fund.investment.instruction.domain.model.eventhandler.saga.util.create;

import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrVo;

/**
 * @Author dongkw
 * @Date 2020/9/18、5:28 下午
 **/
public interface IStatusHandler {

    void handler(IstrVo istrVo);

}
