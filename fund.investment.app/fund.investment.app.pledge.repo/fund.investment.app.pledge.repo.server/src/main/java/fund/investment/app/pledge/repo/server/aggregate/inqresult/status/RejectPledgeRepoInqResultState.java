package fund.investment.app.pledge.repo.server.aggregate.inqresult.status;

import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateConfirmInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateFailedInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateInquiryResultCmd;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateConfirmedEvt;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateEvt;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateFailedEvt;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inqresult.api.command.UpdateConfirmInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.UpdateFailInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.UpdateInquiryResultCmd;
import fund.investment.basic.inqresult.api.enumeration.InquiryResultStatus;
import fund.investment.basic.inqresult.server.aggregate.InquiryResultAggregate;
import fund.investment.basic.inqresult.server.aggregate.status.InquiryResultState;
import org.axonframework.modelling.command.AggregateLifecycle;

public class RejectPledgeRepoInqResultState extends InquiryResultState {
    public RejectPledgeRepoInqResultState() {
        super(InquiryResultStatus.REJECT);
    }
    @Override
    public void update(InquiryResultAggregate aggregate, UpdateInquiryResultCmd cmd) {
        PRUpdateInquiryResultCmd updateInquiryResultCmd = (PRUpdateInquiryResultCmd) cmd;
        PRInquiryResultUpdateEvt evt = new PRInquiryResultUpdateEvt();
        BeanUtils.copyPropertiesIgnoreNull(updateInquiryResultCmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void updateConfirm(InquiryResultAggregate aggregate, UpdateConfirmInquiryResultCmd cmd) {
        PRUpdateConfirmInquiryResultCmd updateInquiryResultCmd = (PRUpdateConfirmInquiryResultCmd) cmd;
        PRInquiryResultUpdateConfirmedEvt evt = new PRInquiryResultUpdateConfirmedEvt();
        BeanUtils.copyPropertiesIgnoreNull(updateInquiryResultCmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void updateFail(InquiryResultAggregate aggregate, UpdateFailInquiryResultCmd cmd) {
        PRUpdateFailedInquiryResultCmd updateInquiryResultCmd = (PRUpdateFailedInquiryResultCmd) cmd;
        PRInquiryResultUpdateFailedEvt evt = new PRInquiryResultUpdateFailedEvt();
        BeanUtils.copyPropertiesIgnoreNull(updateInquiryResultCmd, evt);
        AggregateLifecycle.apply(evt);
    }
}
