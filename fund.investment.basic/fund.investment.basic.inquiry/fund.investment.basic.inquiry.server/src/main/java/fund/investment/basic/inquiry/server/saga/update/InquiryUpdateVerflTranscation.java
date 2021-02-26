package fund.investment.basic.inquiry.server.saga.update;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inquiry.server.saga.InquiryVo;
import fund.investment.gateway.api.book.command.instruction.RollbackVerfIstrUpdateCmd;
import fund.investment.gateway.api.book.command.instruction.VerfIstrUpdateCmd;
import fund.investment.gateway.api.book.event.instruction.IstrVerfUpdateFailedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfUpdateRollBackedEvt;
import fund.investment.gateway.api.book.event.instruction.IstrVerfUpdateSucceedEvt;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InquiryUpdateVerflTranscation extends TransactionUnit {
    private InquiryVo vo;

    public InquiryUpdateVerflTranscation(InquiryVo vo) {
        this.vo = vo;
        eventList.add(IstrVerfUpdateRollBackedEvt.class);
        eventList.add(IstrVerfUpdateFailedEvt.class);
        eventList.add(IstrVerfUpdateSucceedEvt.class);
    }

    @Override
    public void start() {

        VerfIstrUpdateCmd cmd = new VerfIstrUpdateCmd(vo.getUnitId(), vo.getInquiryId(), vo.getAmount());
        CommandGatewayFactory.getCommandGateway().send(cmd);
}

    @Override
    public void rollback() {
        RollbackVerfIstrUpdateCmd cmd = new RollbackVerfIstrUpdateCmd(vo.getUnitId(), vo.getAmount(), vo.getInquiryId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
}

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(IstrVerfUpdateSucceedEvt.class)) {
            status = Status.SUCCEED;
        } else if (event.getClass().isAssignableFrom(IstrVerfUpdateFailedEvt.class)) {
            status = Status.FAILED;
        } else if (event.getClass().isAssignableFrom(IstrVerfUpdateRollBackedEvt.class)) {
            status = Status.FAILED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
