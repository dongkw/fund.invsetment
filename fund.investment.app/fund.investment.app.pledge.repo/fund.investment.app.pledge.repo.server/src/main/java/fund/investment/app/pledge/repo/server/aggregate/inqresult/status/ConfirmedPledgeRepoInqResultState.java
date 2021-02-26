package fund.investment.app.pledge.repo.server.aggregate.inqresult.status;

import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateConfirmInquiryResultCmd;
import fund.investment.app.pledge.repo.api.command.inqresult.PRUpdateFailedInquiryResultCmd;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateConfirmedEvt;
import fund.investment.app.pledge.repo.api.event.inqresult.PRInquiryResultUpdateFailedEvt;
import fund.investment.basic.common.util.BeanUtils;
import fund.investment.basic.inqresult.api.command.*;
import fund.investment.basic.inqresult.api.event.InquiryResultBackEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultFinishEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultRejectEvt;
import fund.investment.basic.inqresult.server.aggregate.InquiryResultAggregate;
import fund.investment.basic.inqresult.server.aggregate.status.ConfirmedInquiryResultState;
import org.axonframework.modelling.command.AggregateLifecycle;

public class ConfirmedPledgeRepoInqResultState extends ConfirmedInquiryResultState {

//    @Override
//    public void update(InquiryResultAggregate aggregate, UpdateInquiryResultCmd cmd) {
//        PRUpdateInquiryResultCmd updateInquiryResultCmd = (PRUpdateInquiryResultCmd) cmd;
//        PRInquiryResultUpdateEvt evt = new PRInquiryResultUpdateEvt();
//        BeanUtils.copyPropertiesIgnoreNull(updateInquiryResultCmd, evt);
//        AggregateLifecycle.apply(evt);
//    }

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

    @Override
    public void back(InquiryResultAggregate aggregate, BackInquiryResultCmd cmd) {
        InquiryResultBackEvt evt = new InquiryResultBackEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void reject(InquiryResultAggregate aggregate, RejectInquiryResultCmd cmd) {
        InquiryResultRejectEvt evt = new InquiryResultRejectEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }
    @Override
    public void finish(InquiryResultAggregate aggregate, FinishInquiryResultCmd cmd) {
        InquiryResultFinishEvt evt = new InquiryResultFinishEvt();
        BeanUtils.copyPropertiesIgnoreNull(cmd, evt);
        AggregateLifecycle.apply(evt);
    }

}
