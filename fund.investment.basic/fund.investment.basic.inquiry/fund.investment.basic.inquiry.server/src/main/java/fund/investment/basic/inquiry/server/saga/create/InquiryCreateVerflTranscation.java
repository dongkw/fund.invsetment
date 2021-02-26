package fund.investment.basic.inquiry.server.saga.create;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inquiry.server.saga.InquiryVo;
import fund.investment.gateway.api.book.command.instruction.RollbackVerfIstrCmd;
import fund.investment.gateway.api.book.command.instruction.VerfIstrCmd;
import fund.investment.gateway.api.book.event.instruction.IstrVerfFailedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfRollBackedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfSucceedEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InquiryCreateVerflTranscation extends TransactionUnit {
    private InquiryVo vo;

    public InquiryCreateVerflTranscation(InquiryVo vo) {
        this.vo = vo;
        eventList.add(IstrVerfRollBackedEvt.class);
        eventList.add(IstrVerfFailedEvt.class);
        eventList.add(IstrVerfSucceedEvt.class);
    }

    @Override
    public void start() {

        VerfIstrCmd cmd = new VerfIstrCmd(vo.getUnitId(), vo.getInquiryId(), vo.getAmount());
        CommandGatewayFactory.getCommandGateway().send(cmd);
}

    @Override
    public void rollback() {
        RollbackVerfIstrCmd cmd = new RollbackVerfIstrCmd(vo.getUnitId(), vo.getAmount(), vo.getInquiryId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
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
