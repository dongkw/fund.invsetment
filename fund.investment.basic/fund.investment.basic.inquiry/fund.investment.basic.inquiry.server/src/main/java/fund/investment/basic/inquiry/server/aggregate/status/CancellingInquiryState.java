package fund.investment.basic.inquiry.server.aggregate.status;

import fund.investment.basic.inquiry.api.command.CancelConfInquiryCmd;
import fund.investment.basic.inquiry.api.enumeration.InquiryStatus;
import fund.investment.basic.inquiry.api.event.InquiryCancelledEvt;
import fund.investment.basic.inquiry.server.aggregate.InquiryAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CancellingInquiryState extends InquiryState {

    public CancellingInquiryState() {
        super(InquiryStatus.CANCELLING);
    }

    @Override
    public void cancelConfirm(InquiryAggregate aggregate, CancelConfInquiryCmd cmd) {
        InquiryCancelledEvt evt = new InquiryCancelledEvt();
        evt.setId(cmd.getId());
        evt.setSkId(aggregate.getSkId());
        evt.setSkInquiry(aggregate.getSkInquiry());
        evt.setCancelType(cmd.getCancelType());
        evt.setCancelMsg(cmd.getCancelMsg());
        evt.setChLastModifiedId(cmd.getChLastModifiedId());
        evt.setTsLastModifiedTime(cmd.getTsLastModifiedTime());
        evt.setRequestId(cmd.getRequestId());
        AggregateLifecycle.apply(evt);
    }

}
