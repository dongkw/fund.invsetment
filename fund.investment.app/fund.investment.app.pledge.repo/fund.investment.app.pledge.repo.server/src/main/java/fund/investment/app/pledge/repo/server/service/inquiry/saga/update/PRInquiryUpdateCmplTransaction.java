package fund.investment.app.pledge.repo.server.service.inquiry.saga.update;


import fund.investment.app.pledge.repo.api.command.inquiry.PRUpdateConfirmInquiryCmd;
import fund.investment.app.pledge.repo.api.command.inquiry.PRUpdateFailedInquiryCmd;
import fund.investment.app.pledge.repo.api.event.inquiry.PRInquiryUpdateEvt;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inquiry.server.saga.update.InquiryUpdateCmplTransaction;
import fund.investment.gateway.api.compliance.command.inquriy.pledge.PRModifyCmplInquiryCmd;

import java.util.Objects;

public class PRInquiryUpdateCmplTransaction extends InquiryUpdateCmplTransaction {
    private PRInquiryUpdateEvt evt;

    public PRInquiryUpdateCmplTransaction(PRInquiryUpdateEvt evt) {
        this.evt = evt;
    }

    @Override
    protected DomainCommand buildStartCmd() {
        PRModifyCmplInquiryCmd cmd = new PRModifyCmplInquiryCmd();
        BeanUtils.copyProperties(evt, cmd);
        return cmd;
    }

    @Override
    public DomainCommand fillCmd(DomainCommand domainCommand) {
        if (Objects.equals(status, Status.SUCCEED)) {
            PRUpdateConfirmInquiryCmd cmd = (PRUpdateConfirmInquiryCmd) domainCommand;
            cmd.setChInstrSource(cInstrSource);
            cmd.setChSourceKey(cSourceKey);
            cmd.setChSourceNo(cSourceNo);
            return cmd;
        } else if (Objects.equals(status, Status.FAILED)) {
            PRUpdateFailedInquiryCmd cmd = (PRUpdateFailedInquiryCmd) domainCommand;
            cmd.setRiskRiskInfos(riskRiskInfos);
            return cmd;
        } else {
            return domainCommand;
        }
    }
}
