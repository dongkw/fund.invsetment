package fund.investment.basic.inqresult.server.aggregate.status;


import fund.investment.basic.inqresult.api.command.CancelConfInquiryResultCmd;
import fund.investment.basic.inqresult.api.enumeration.InquiryResultStatus;
import fund.investment.basic.inqresult.api.event.InquiryResultCancelledEvt;
import fund.investment.basic.inqresult.server.aggregate.InquiryResultAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

@Slf4j
public class CancellingInquiryResultState extends InquiryResultState {

    public CancellingInquiryResultState() {
        super(InquiryResultStatus.CANCELLING);
    }

    @Override
    public void cancelConfirm(InquiryResultAggregate aggregate, CancelConfInquiryResultCmd cmd) {
        InquiryResultCancelledEvt evt = new InquiryResultCancelledEvt();
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
