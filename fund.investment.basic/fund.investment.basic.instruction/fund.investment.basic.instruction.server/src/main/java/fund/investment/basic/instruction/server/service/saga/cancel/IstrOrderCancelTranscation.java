package fund.investment.basic.instruction.server.service.saga.cancel;

import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class IstrOrderCancelTranscation extends TransactionUnit {
    private Map<String, Boolean> orderFinishedMap;
    private IstrVo istrVo;

    public IstrOrderCancelTranscation(Map<String, Boolean> orderFinishedMap, IstrVo istrVo) {
        this.orderFinishedMap = orderFinishedMap;
        this.istrVo = istrVo;
//        eventList.add(OrderCancelledEvt.class);
//        eventList.add(OrderFilledEvt.class);
    }

    @Override
    public void start() {
        orderFinishedMap.entrySet().forEach(t -> {
            //TODO 不知道干什么的
//            CancelOrderCmd cancelOrderCmd = new CancelOrderCmd(t.getKey(),
//                    istrVo.getIstrId(),
//                    istrVo.getTradeType().name(),
//                    istrVo.getUnitId(),
//                    11L,
//                    null,
//                    null,
//                    null,
//                    null);
//            log.debug("saga send:{}", cancelOrderCmd);
//            CommandGatewayFactory.getCommandGateway().send(cancelOrderCmd);
        });
    }

    @Override
    public void rollback() {
    }

    @Override
    public void eventHandler(DomainEvent event) {

//        if (event.getClass().isAssignableFrom(OrderFilledEvt.class) || event.getClass().isAssignableFrom(OrderCancelledEvt.class)) {
//            orderFinishedMap.putIfAbsent(event.getId(), true);
//            if (orderFinishedMap.values().stream().allMatch(t -> t)) {
//                status = Status.SUCCEED;
//            }
//        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}