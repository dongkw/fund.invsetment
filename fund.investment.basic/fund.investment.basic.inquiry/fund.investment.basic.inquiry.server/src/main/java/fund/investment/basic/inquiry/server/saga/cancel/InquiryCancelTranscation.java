package fund.investment.basic.inquiry.server.saga.cancel;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inquiry.api.command.CancelConfInquiryCmd;
import fund.investment.basic.inquiry.api.event.InquiryCancelledEvt;
import fund.investment.basic.inquiry.server.saga.InquiryVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InquiryCancelTranscation extends TransactionUnit {
    private InquiryVo vo;

    private String cancelType;

    private String cancelMsg;

    public InquiryCancelTranscation(InquiryVo vo, String cancelType, String cancelMsg) {
        this.vo = vo;
        this.cancelType = cancelType;
        this.cancelMsg = cancelMsg;
        eventList.add(InquiryCancelledEvt.class);
    }

    @Override
    public void start() {
        CancelConfInquiryCmd cmd = new CancelConfInquiryCmd();
        cmd.setId(vo.getInquiryId());
        cmd.setCancelType(cancelType);
        cmd.setCancelMsg(cancelMsg);
        cmd.setChLastModifiedId(vo.getLastmodifiedId());
        cmd.setTsLastModifiedTime(vo.getLastmodifiedTime());
        cmd.setRequestId(vo.getRequestId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void rollback() {
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(InquiryCancelledEvt.class)) {
            status = Status.SUCCEED;
        }
    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
