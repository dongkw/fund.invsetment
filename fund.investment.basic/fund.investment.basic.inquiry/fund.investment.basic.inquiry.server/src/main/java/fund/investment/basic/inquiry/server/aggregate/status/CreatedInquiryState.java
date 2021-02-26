package fund.investment.basic.inquiry.server.aggregate.status;

import fund.investment.basic.inquiry.api.command.CreateConfirmInquiryCmd;
import fund.investment.basic.inquiry.api.command.CreateFailInquiryCmd;
import fund.investment.basic.inquiry.api.enumeration.InquiryStatus;
import fund.investment.basic.inquiry.api.event.InquiryConfirmedEvt;
import fund.investment.basic.inquiry.api.event.InquiryFailedEvt;
import fund.investment.basic.inquiry.server.aggregate.InquiryAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CreatedInquiryState extends InquiryState {

    public CreatedInquiryState() {
        super(InquiryStatus.CREATED);
    }

    @Override
    public void createConfirm(InquiryAggregate aggregate, CreateConfirmInquiryCmd confirmCmd) {
        InquiryConfirmedEvt evt = new InquiryConfirmedEvt();
        evt.setId(confirmCmd.getId());
        evt.setSkId(aggregate.getSkId());
        evt.setSkInquiry(aggregate.getSkInquiry());
        evt.setChLastModifiedId(confirmCmd.getChLastModifiedId());
        evt.setTsLastModifiedTime(confirmCmd.getTsLastModifiedTime());
        evt.setRequestId(confirmCmd.getRequestId());
        evt.setChInstrSource(confirmCmd.getChInstrSource());
        evt.setChSourceKey(confirmCmd.getChSourceKey());
        evt.setChSourceNo(confirmCmd.getChSourceNo());
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void createFail(InquiryAggregate aggregate, CreateFailInquiryCmd cmd) {
        InquiryFailedEvt evt = new InquiryFailedEvt();
        evt.setId(cmd.getId());
        evt.setSkId(aggregate.getSkId());
        evt.setSkInquiry(aggregate.getSkInquiry());
        evt.setFailCode(cmd.getFailCode());
        evt.setFailMsg(cmd.getFailMsg());
        evt.setChLastModifiedId(cmd.getChLastModifiedId());
        evt.setTsLastModifiedTime(cmd.getTsLastModifiedTime());
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
    }
}
