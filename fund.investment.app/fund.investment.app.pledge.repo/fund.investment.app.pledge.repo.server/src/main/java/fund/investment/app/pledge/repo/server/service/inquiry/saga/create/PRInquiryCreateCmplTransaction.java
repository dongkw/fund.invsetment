package fund.investment.app.pledge.repo.server.service.inquiry.saga.create;


import fund.investment.app.pledge.repo.api.event.inquiry.PRInquiryCreatedEvt;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inquiry.server.saga.create.InquiryCreateCmplTranscation;
import fund.investment.gateway.api.compliance.command.inquriy.pledge.PRDirectCmplInquiryCmd;

public class PRInquiryCreateCmplTransaction extends InquiryCreateCmplTranscation {
    private PRInquiryCreatedEvt evt;

    public PRInquiryCreateCmplTransaction(PRInquiryCreatedEvt evt) {
        this.evt = evt;
    }

    @Override
    protected DomainCommand buildStartCmd() {
        PRDirectCmplInquiryCmd cmd = new PRDirectCmplInquiryCmd();
        BeanUtils.copyProperties(evt, cmd);
        return cmd;
    }
}
