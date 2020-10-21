package fund.investment.instruction.domain.model.eventhandler.saga.util.create;

import fund.investment.instruction.domain.model.eventhandler.saga.util.create.impl.DefaultImpl;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrSagaStatus;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author dongkw
 * @Date 2020/9/18、5:42 下午
 **/
@Component
@Slf4j
public class HandlerFactory {

    private static final Map<Set<IstrSagaStatus>, IStatusHandler> handlerMap = new HashMap<>();

    public static void register(List<IstrSagaStatus> statuses, IStatusHandler handler) {
        handlerMap.put(new HashSet<>(statuses), handler);
    }

    public void handler(IstrVo istrVo) {
        log.debug("now istrId,{} status:{},", istrVo.getIstrId(), istrVo.getStatuses());
        handlerMap.getOrDefault(istrVo.getStatuses(), new DefaultImpl()).handler(istrVo);
    }

}
