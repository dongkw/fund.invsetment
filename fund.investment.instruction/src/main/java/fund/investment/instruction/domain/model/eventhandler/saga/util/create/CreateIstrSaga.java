package fund.investment.instruction.domain.model.eventhandler.saga.util.create;


import com.fasterxml.jackson.annotation.JsonProperty;
import fund.investment.infrastructure.book.domain.model.command.instruction.VerfIstrCmd;
import fund.investment.infrastructure.book.domain.model.event.instruction.IstrVerfFailedEvt;
import fund.investment.infrastructure.book.domain.model.event.instruction.IstrVerfRollBackedEvt;
import fund.investment.infrastructure.book.domain.model.event.instruction.IstrVerfSucceedEvt;
import fund.investment.infrastructure.compliance.domain.model.command.instruction.CmplIstrCmd;
import fund.investment.infrastructure.compliance.domain.model.event.instruction.IstrCmplFailedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.instruction.IstrCmplRollbackedEvt;
import fund.investment.infrastructure.compliance.domain.model.event.instruction.IstrCmplSucceedEvt;
import fund.investment.infrastructure.instruction.domain.model.event.IstrCreatedEvt;
import fund.investment.instruction.domain.model.eventhandler.saga.util.InstructionSaga;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrSagaStatus;
import fund.investment.instruction.domain.model.eventhandler.saga.util.create.vo.IstrVo;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashSet;

/**
 * @Author dongkw
 * @Date 2020/9/17、13:38
 **/
//@Saga
@Slf4j
public class CreateIstrSaga extends InstructionSaga {

    @JsonProperty
    IstrVo istrVo;

    @Autowired
    transient HandlerFactory factory;


    public CreateIstrSaga() {
        istrVo = new IstrVo();
    }

    public void startSaga(IstrCreatedEvt evt) {
        log.debug("saga start receive:{}", evt);
        istrVo.setIstrId(evt.getId());
        istrVo.setUnitId(evt.getUnitId());
        istrVo.setSecurityCode(evt.getSecurityCode());
        istrVo.setAmount(new BigDecimal(evt.getQuantity()));
        istrVo.setStatuses(new HashSet<>());
        CmplIstrCmd cmplCmd = new CmplIstrCmd(istrVo.getSecurityCode(), istrVo.getIstrId(), istrVo.getAmount());
        commandGateway.send(cmplCmd);
        log.debug("saga send:{}", cmplCmd);
        VerfIstrCmd verfCmd = new VerfIstrCmd(istrVo.getUnitId(), istrVo.getIstrId(), istrVo.getAmount());
        commandGateway.send(verfCmd);
        log.debug("saga send:{}", verfCmd);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplSucceedEvt evt) {
        log.debug("saga receive:{}", evt);
        istrVo.getStatuses().add(IstrSagaStatus.CMPL_SUCC);
        factory.handler(istrVo);

    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplFailedEvt evt) {
        log.debug("saga receive:{}", evt);

        istrVo.getStatuses().add(IstrSagaStatus.CMPL_FAIL);
        factory.handler(istrVo);
    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfSucceedEvt evt) {
        log.debug("saga receive:{}", evt);

        istrVo.getStatuses().add(IstrSagaStatus.VERF_SUCC);
        factory.handler(istrVo);
    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfFailedEvt evt) {
        log.debug("saga receive:{}", evt);

        istrVo.getStatuses().add(IstrSagaStatus.VERF_FAIL);
        factory.handler(istrVo);
    }


    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrCmplRollbackedEvt evt) {
        log.debug("saga receive:{}", evt);

        istrVo.getStatuses().add(IstrSagaStatus.ROLLBACK_CMPL);
        factory.handler(istrVo);
    }

    @SagaEventHandler(associationProperty = "istrId", keyName = "id")
    public void handler(IstrVerfRollBackedEvt evt) {
        log.debug("saga receive:{}", evt);

        istrVo.getStatuses().add(IstrSagaStatus.ROLLBACK_VREF);
        factory.handler(istrVo);
    }


}
