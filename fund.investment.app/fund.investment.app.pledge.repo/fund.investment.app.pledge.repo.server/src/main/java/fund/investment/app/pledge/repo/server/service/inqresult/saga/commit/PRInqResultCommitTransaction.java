package fund.investment.app.pledge.repo.server.service.inqresult.saga.commit;


import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultCommitEvt;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inqresult.server.saga.create.InqResultCreateCmplTransaction;
import fund.investment.gateway.api.compliance.command.inqresult.pledge.PRSubmitCmplInqResultCmd;

public class PRInqResultCommitTransaction extends InqResultCreateCmplTransaction {
    private PRInquiryResultCommitEvt evt;

    public PRInqResultCommitTransaction(PRInquiryResultCommitEvt evt) {
        this.evt = evt;
    }

    @Override
    protected DomainCommand buildStartCmd() {
        PRSubmitCmplInqResultCmd cmd = new PRSubmitCmplInqResultCmd();
        BeanUtils.copyProperties(evt, cmd);
        return cmd;
    }
}
