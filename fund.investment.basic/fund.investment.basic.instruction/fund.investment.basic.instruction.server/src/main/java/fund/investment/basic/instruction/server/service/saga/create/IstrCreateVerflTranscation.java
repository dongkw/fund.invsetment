package fund.investment.basic.instruction.server.service.saga.create;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.gateway.api.book.command.instruction.RollbackVerfIstrCmd;
import fund.investment.gateway.api.book.command.instruction.VerfIstrCmd;
import fund.investment.gateway.api.book.event.instruction.IstrVerfFailedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfRollBackedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfSucceedEvt;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

@Slf4j
public class IstrCreateVerflTranscation extends TransactionUnit {
    private IstrVo istrVo;
    private final transient CommandGateway commandGateway;
    private DomainEvent result;

    public IstrCreateVerflTranscation(IstrVo vo, CommandGateway commandGateway) {
        this.istrVo = vo;
        this.commandGateway = commandGateway;
        eventList.add(IstrVerfRollBackedEvt.class);
        eventList.add(IstrVerfFailedEvt.class);
        eventList.add(IstrVerfSucceedEvt.class);
    }

    @Override
    public void start() {

        VerfIstrCmd cmd = new VerfIstrCmd(istrVo.getUnitId(), istrVo.getIstrId(), istrVo.getAmount());
        commandGateway.send(cmd);
    }

    @Override
    public void rollback() {
        RollbackVerfIstrCmd cmd = new RollbackVerfIstrCmd(istrVo.getUnitId(), istrVo.getAmount(), istrVo.getIstrId());
        commandGateway.send(cmd);
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(IstrVerfSucceedEvt.class)) {
            status = Status.SUCCEED;
        } else if (event.getClass().isAssignableFrom(IstrVerfFailedEvt.class)) {
            status = Status.FAILED;
        } else if (event.getClass().isAssignableFrom(IstrVerfRollBackedEvt.class)) {
            status = Status.FAILED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
