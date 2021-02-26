package fund.investment.app.pledge.repo.server.service.inqresult.saga.update;

import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateConfirmInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateFailedInquiryResultCmd;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateEvt;
import fund.investment.basic.common.DomainCommand;
import fund.investment.basic.common.saga.Status;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inqresult.server.saga.update.InqResultUpdateCmplTranscation;
import fund.investment.gateway.api.compliance.command.inqresult.pledge.PRModifyCmplInqResultCmd;

import java.util.Objects;

public class PRInqResultUpdateTransaction extends InqResultUpdateCmplTranscation {
    private PRInquiryResultUpdateEvt evt;

    public PRInqResultUpdateTransaction(PRInquiryResultUpdateEvt evt) {
        this.evt = evt;
    }

    @Override
    protected DomainCommand buildStartCmd() {
        PRModifyCmplInqResultCmd cmd = new PRModifyCmplInqResultCmd();
        BeanUtils.copyProperties(evt, cmd);
        return cmd;
    }

    @Override
    public DomainCommand fillCmd(DomainCommand domainCommand) {
        if (Objects.equals(status, Status.SUCCEED)) {
            PRUpdateConfirmInquiryResultCmd cmd = (PRUpdateConfirmInquiryResultCmd) domainCommand;

            return cmd;
        } else if (Objects.equals(status, Status.FAILED)) {
            PRUpdateFailedInquiryResultCmd cmd = (PRUpdateFailedInquiryResultCmd) domainCommand;
            cmd.setRiskInfos(riskInfos);
            return cmd;
        } else {
            return domainCommand;
        }
    }
}
