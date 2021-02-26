package fund.investment.basic.instruction.server.service.saga.cancel;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.instruction.api.command.CancelConfIstrCmd;
import fund.investment.basic.instruction.api.event.IstrCancelledEvt;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IstrCancelTranscation extends TransactionUnit {
    private IstrVo istrVo;

    private String cancelType;

    private String cancelMsg;

    public IstrCancelTranscation(IstrVo vo, String cancelType, String cancelMsg) {
        this.istrVo = vo;
        this.cancelType = cancelType;
        this.cancelMsg = cancelMsg;
        eventList.add(IstrCancelledEvt.class);
    }

    @Override
    public void start() {
        CancelConfIstrCmd cmd = new CancelConfIstrCmd();
        cmd.setId(istrVo.getIstrId());
        cmd.setRequestId(istrVo.getRequestId());
        cmd.setCancelType(cancelType);
        cmd.setCancelMsg(cancelMsg);
        cmd.setChLastModifiedId(istrVo.getLastmodifiedId());
        cmd.setTsLastModifiedTime(istrVo.getLastmodifiedTime());
        cmd.setChInstrSource(istrVo.getChInstrSource());
        cmd.setChSourceKey(istrVo.getChSourceKey());
        cmd.setChSourceNo(istrVo.getChSourceNo());
        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void rollback() {
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(IstrCancelledEvt.class)) {
            status = Status.SUCCEED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
