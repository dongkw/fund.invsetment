package fund.investment.app.pledge.repo.server.service.instruction.saga;


import fund.investment.app.pledge.repo.api.event.instruction.PRIstrCancellingEvt;
import fund.investment.app.pledge.repo.server.service.instruction.saga.cancel.PRInstrCancelCmplTranscationImpl;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.ITransaction;
import fund.investment.basic.common.saga.ParallelTransaction;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.instruction.api.command.CancelConfIstrCmd;
import fund.investment.basic.instruction.api.command.RollbackCancelIstrCmd;
import fund.investment.basic.instruction.api.enumeration.OrderStatus;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.basic.instruction.server.service.saga.cancel.CancelIstrSaga;
import fund.investment.basic.instruction.server.service.saga.cancel.IstrCancelCmplTranscation;
import fund.investment.basic.instruction.server.service.saga.cancel.IstrCancelVerfTranscation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * @Author dongkw
 * @Date 2020/10/9、5:37 下午
 **/

@Saga
@Slf4j
public class PRCancelIstrSaga extends CancelIstrSaga {

    private IstrVo istrVo;
    private PRIstrCancellingEvt evt;
    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handler(PRIstrCancellingEvt evt) {
        log.debug("Cancel saga start ,receive:{}", evt);
        transaction = genCancelTransaction(evt);
        transaction.start();
    }

    public ITransaction genCancelTransaction(PRIstrCancellingEvt evt) {
        istrVo = createIstrVo(evt);
        this.evt = evt;
        ParallelTransaction parallelTransaction = new ParallelTransaction(Arrays.asList(new IstrCancelCmplTranscation(istrVo), new IstrCancelVerfTranscation(istrVo)));
//        if (hasUnFinishOrder(evt)) {
//            List<Order> orders = evt.getOrders();
//            Map<String, Boolean> unFinishMap = orders.stream().filter(t -> Objects.equals(t.getStatus(), OrderStatus.UNDEFINED)
//                    || Objects.equals(t.getStatus(), OrderStatus.PART_ENTRUSTED)).collect(Collectors.toMap(Order::getId, v -> false));
//            return new SerialTransaction(Arrays.asList(new IstrOrderCancelTranscation(unFinishMap, istrVo), new IstrCancelTranscation(istrVo, evt.getCancelType(),evt.getCancelMsg()), parallelTransaction));
//        } else {
//            return new SerialTransaction(Arrays.asList(new IstrCancelTranscation(istrVo, evt.getCancelType(),evt.getCancelMsg()), parallelTransaction));
//        }
        return new ParallelTransaction(Collections.singletonList(new PRInstrCancelCmplTranscationImpl(istrVo)));
    }

    private IstrVo createIstrVo(PRIstrCancellingEvt evt) {
        IstrVo istrVo = new IstrVo();
        istrVo.setIstrId(evt.getId());
        istrVo.setRequestId(evt.getRequestId());
        istrVo.setLastmodifiedId(evt.getChLastModifiedId());
        istrVo.setLastmodifiedTime(evt.getTsLastModifiedTime());
        istrVo.setChInstrSource(evt.getChInstrSource());
        istrVo.setChSourceKey(evt.getChSourceKey());
        istrVo.setChSourceNo(evt.getChSourceNo());
        istrVo.setSkId(evt.getSkId());
        istrVo.setUserId(evt.getUserId());
        return istrVo;
    }


    @Override
    protected void checkIsFinish() {
        DomainCommand command = createCmd(transaction.getStatus());
        if (command != null) {
            CommandGatewayFactory.getCommandGateway().send(transaction.fillCmd(command));
            log.debug("saga send：{}", command);
            SagaLifecycle.end();
            log.debug("----------saga end--------：{}", evt.getId());
        }
    }

    private DomainCommand createCmd(Status status) {
        if (status == Status.SUCCEED) {
            CancelConfIstrCmd cmd = new CancelConfIstrCmd();
            cmd.setId(evt.getId());
            cmd.setSkId(evt.getSkId());
            cmd.setRequestId(evt.getRequestId());
            return cmd;
        } else {
            RollbackCancelIstrCmd cmd = new RollbackCancelIstrCmd();
            cmd.setId(evt.getId());
            cmd.setSkId(evt.getSkId());
            cmd.setRequestId(evt.getRequestId());
            return cmd;
        }
    }

    private boolean hasUnFinishOrder(PRIstrCancellingEvt evt) {
        if (Objects.isNull(evt.getOrders())) {
            return false;
        }
        if (CollectionUtils.isEmpty(evt.getOrders())) {
            return false;
        }
        if (evt.getOrders().stream().anyMatch(t -> Objects.equals(t.getStatus(), OrderStatus.UNDEFINED)
                || Objects.equals(t.getStatus(), OrderStatus.PART_ENTRUSTED))) {
            return false;
        }
        return true;
    }
}
