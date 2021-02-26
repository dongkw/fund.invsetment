package fund.investment.basic.instruction.server.service.saga.cancel;

import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.instruction.server.service.saga.IstrVo;
import fund.investment.gateway.api.book.command.VerificationCommand;
import fund.investment.gateway.api.book.command.instruction.CancelVerfIstrCmd;
import fund.investment.gateway.api.book.event.instruction.IstrVerfCancelledEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IstrCancelVerfTranscation extends TransactionUnit {
    private IstrVo istrVo;

    public IstrCancelVerfTranscation(IstrVo vo) {
        this.istrVo = vo;
        eventList.add(IstrVerfCancelledEvt.class);
    }

    @Override
    public void start() {
        VerificationCommand cmd = new CancelVerfIstrCmd(istrVo.getUnitId(), istrVo.getIstrId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
}

    @Override
    public void rollback() {
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(IstrVerfCancelledEvt.class)) {
            status = Status.SUCCEED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
