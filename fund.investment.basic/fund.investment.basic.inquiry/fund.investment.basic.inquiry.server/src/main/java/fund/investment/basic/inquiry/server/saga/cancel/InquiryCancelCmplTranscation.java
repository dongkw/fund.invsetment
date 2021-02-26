package fund.investment.basic.inquiry.server.saga.cancel;


import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.DomainEvent;
import fund.investment.basic.common.saga.CommandGatewayFactory;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.saga.TransactionUnit;
import fund.investment.basic.inquiry.server.saga.InquiryVo;
import fund.investment.gateway.api.compliance.command.inquriy.CancelCmplInquiryCmd;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplCancelFailEvt;
import fund.investment.gateway.api.compliance.event.inquiry.InquiryCmplCancelSucceedEvt;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class InquiryCancelCmplTranscation extends TransactionUnit {
    private InquiryVo vo;

    public InquiryCancelCmplTranscation(InquiryVo vo) {
        this.vo = vo;
        eventList.add(InquiryCmplCancelSucceedEvt.class);
        eventList.add(InquiryCmplCancelFailEvt.class);
    }

    @Override
    public void start() {
        CancelCmplInquiryCmd cmd = new CancelCmplInquiryCmd();
        cmd.setId(vo.getInquiryId());
        cmd.setChLastModifiedId(vo.getLastmodifiedId());
        cmd.setTsLastModifiedTime(new Date());
        cmd.setRequestId(vo.getRequestId());
        cmd.setSkInquiry(vo.getInquiryId());
        cmd.setSkId(vo.getSkId());
        cmd.setUserId(vo.getUserId());
        CommandGatewayFactory.getCommandGateway().send(cmd);
    }

    @Override
    public void rollback() {
    }

    @Override
    public void eventHandler(DomainEvent event) {
        if (event.getClass().isAssignableFrom(InquiryCmplCancelSucceedEvt.class)) {
            status = Status.SUCCEED;
        }
        if (event.getClass().isAssignableFrom(InquiryCmplCancelFailEvt.class)) {
            status = Status.FAILED;
        }

    }

    @Override
    public DomainCommand fillCmd(DomainCommand command) {
        return null;
    }
}
