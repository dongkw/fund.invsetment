package fund.investment.app.pledge.repo.server.aggregate.inqresult.status;

import fund.investment.app.pledge.repo.api.command.inqresult.PRCommitInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateInquiryResultCmd;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultCommitEvt;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultDraUpdateEvt;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inqresult.api.command.CommitInqResultCmd;
import fund.investment.basic.inqresult.api.command.UpdateInquiryResultCmd;
import fund.investment.basic.inqresult.server.aggregate.InquiryResultAggregate;
import fund.investment.basic.inqresult.server.aggregate.status.CreatedInquiryResultState;
import org.axonframework.modelling.command.AggregateLifecycle;

public class CreatedPledgeRepoInqResultState extends CreatedInquiryResultState {

    @Override
    public void update(InquiryResultAggregate aggregate, UpdateInquiryResultCmd cmd) {
        PRUpdateInquiryResultCmd updateInquiryResultCmd = (PRUpdateInquiryResultCmd) cmd;
        PRInquiryResultDraUpdateEvt evt = new PRInquiryResultDraUpdateEvt();
        BeanUtils.copyPropertiesIgnoreNull(updateInquiryResultCmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void commit(InquiryResultAggregate aggregate, CommitInqResultCmd cmd) {
        PRCommitInquiryResultCmd commitInquiryResultCmd = (PRCommitInquiryResultCmd) cmd;
        PRInquiryResultCommitEvt evt = new PRInquiryResultCommitEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
