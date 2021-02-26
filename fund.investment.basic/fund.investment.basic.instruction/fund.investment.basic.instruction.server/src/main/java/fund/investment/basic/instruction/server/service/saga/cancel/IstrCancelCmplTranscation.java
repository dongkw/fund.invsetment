package fund.investment.basic.instruction.server.service.saga.cancel;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.instruction.api.command.CancelConfIstrCmd;
import fund.investment.basic.instruction.api.command.RollbackCancelIstrCmd;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.gateway.api.compliance.command.instruction.CancelComplIstrCmd;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplCancelFailedEvt;
import fund.investment.gateway.api.compliance.event.instruction.IstrCmplCancelledEvt;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class IstrCancelCmplTranscation extends TransactionUnit {
    private IstrVo istrVo;

    public IstrCancelCmplTranscation(IstrVo vo) {
        this.istrVo = vo;
        eventList.add(IstrCmplCancelledEvt.class);
    }

    @Override
    public void start() {
        CancelComplIstrCmd cmd = new CancelComplIstrCmd();
        BeanUtils.copyProperties(istrVo, cmd);
        cmd.setId(istrVo.getIstrId());
        cmd.setSkId(istrVo.getSkId());
        cmd.setSkInstr(istrVo.getIstrId());
        cmd.setUserId(istrVo.getUserId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void rollback() {
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(IstrCmplCancelledEvt.class)) {
            status = Status.SUCCEED;
            IstrCmplCancelledEvt evt = new IstrCmplCancelledEvt();
            istrVo.setIstrId(evt.getIstrId());
            istrVo.setChSourceNo(evt.getChSourceNo());
            istrVo.setChSourceKey(evt.getChSourceKey());
            istrVo.setChInstrSource(evt.getChInstrSource());
        } else if (event.getClass().isAssignableFrom(IstrCmplCancelFailedEvt.class)) {
            status = Status.FAILED;
            IstrCmplCancelFailedEvt evt = new IstrCmplCancelFailedEvt();
            istrVo.setIstrId(evt.getIstrId());
            istrVo.setChSourceNo(evt.getChSourceNo());
            istrVo.setChSourceKey(evt.getChSourceKey());
            istrVo.setChInstrSource(evt.getChInstrSource());
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        if (Objects.equals(status, Status.SUCCEED)) {
            CancelConfIstrCmd cmd = (CancelConfIstrCmd) command;
            cmd.setChInstrSource(istrVo.getChInstrSource());
            cmd.setChSourceKey(istrVo.getChSourceKey());
            cmd.setChSourceNo(istrVo.getChSourceNo());
            return cmd;
        } else {
            RollbackCancelIstrCmd cmd = new RollbackCancelIstrCmd();
            cmd.setChInstrSource(istrVo.getChInstrSource());
            cmd.setChSourceKey(istrVo.getChSourceKey());
            cmd.setChSourceNo(istrVo.getChSourceNo());
            return cmd;
        }
    }
}
