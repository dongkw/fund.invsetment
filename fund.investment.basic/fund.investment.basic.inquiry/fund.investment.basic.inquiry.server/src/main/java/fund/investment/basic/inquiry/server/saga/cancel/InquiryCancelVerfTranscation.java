package fund.investment.basic.inquiry.server.saga.cancel;

import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inquiry.server.saga.InquiryVo;
import fund.investment.gateway.api.book.command.VerificationCommand;
import fund.investment.gateway.api.book.command.instruction.CancelVerfIstrCmd;
import fund.investment.gateway.api.book.event.instruction.IstrVerfCancelledEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InquiryCancelVerfTranscation extends TransactionUnit {
    private InquiryVo vo;

    public InquiryCancelVerfTranscation(InquiryVo vo) {
        this.vo = vo;
        eventList.add(IstrVerfCancelledEvt.class);
    }

    @Override
    public void start() {
        VerificationCommand cmd = new CancelVerfIstrCmd(vo.getUnitId(), vo.getInquiryId());
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
