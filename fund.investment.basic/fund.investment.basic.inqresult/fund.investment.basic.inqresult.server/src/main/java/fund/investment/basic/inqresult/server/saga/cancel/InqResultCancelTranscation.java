package fund.investment.basic.inqresult.server.saga.cancel;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inqresult.api.command.CancelConfInquiryResultCmd;
import fund.investment.basic.inqresult.api.event.InquiryResultCancelledEvt;
import fund.investment.basic.inqresult.server.saga.InqResultVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InqResultCancelTranscation extends TransactionUnit {
    private InqResultVo vo;

    private String cancelType;

    private String cancelMsg;

    public InqResultCancelTranscation(InqResultVo vo, String cancelType, String cancelMsg) {
        this.vo = vo;
        this.cancelType = cancelType;
        this.cancelMsg = cancelMsg;
        eventList.add(InquiryResultCancelledEvt.class);
    }

    @Override
    public void start() {
        CancelConfInquiryResultCmd cmd = new CancelConfInquiryResultCmd();
        cmd.setRequestId(vo.getRequestId());
        cmd.setId(vo.getSkId());
        cmd.setSkInquiry(vo.getSkInquiry());
        cmd.setCancelType(cancelType);
        cmd.setCancelMsg(cancelMsg);
        cmd.setChLastModifiedId(vo.getLastmodifiedId());
        cmd.setTsLastModifiedTime(vo.getLastmodifiedTime());
        CommandGatewayFactory.getCommandGateway().send(cmd);
}

    @Override
    public void rollback() {
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(InquiryResultCancelledEvt.class)) {
            status = Status.SUCCEED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
