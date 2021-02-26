package fund.investment.basic.inqresult.server.aggregate.status;


import fund.investment.basic.inqresult.api.command.CreateConfirmInquiryResultCmd;
import fund.investment.basic.inqresult.api.command.CreateFailInquiryResultCmd;
import fund.investment.basic.inqresult.api.enumeration.InquiryResultStatus;
import fund.investment.basic.inqresult.api.event.InquiryResultConfirmedEvt;
import fund.investment.basic.inqresult.api.event.InquiryResultFailedEvt;
import fund.investment.basic.inqresult.server.aggregate.InquiryResultAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.Date;

@Slf4j
public class CommitInquiryResultState extends InquiryResultState {

    public CommitInquiryResultState() {
        super(InquiryResultStatus.COMMIT);
    }

    @Override
    public void createConfirm(InquiryResultAggregate aggregate, CreateConfirmInquiryResultCmd confirmCmd) {
        InquiryResultConfirmedEvt evt = new InquiryResultConfirmedEvt();
        evt.setId(confirmCmd.getId());
        evt.setSkId(aggregate.getSkId());
        evt.setSkInquiry(aggregate.getSkInquiry());
        evt.setChLastModifiedId(confirmCmd.getChLastModifiedId());
        evt.setTsLastModifiedTime(new Date());
        evt.setRequestId(confirmCmd.getRequestId());
        AggregateLifecycle.apply(evt);
    }

    @Override
    public void createFail(InquiryResultAggregate aggregate, CreateFailInquiryResultCmd cmd) {
        InquiryResultFailedEvt evt = new InquiryResultFailedEvt();
        evt.setId(cmd.getId());
        evt.setSkId(aggregate.getSkId());
        evt.setSkInquiry(aggregate.getSkInquiry());
        evt.setFailCode(cmd.getFailCode());
        evt.setFailMsg(cmd.getFailMsg());
        evt.setChLastModifiedId(cmd.getChLastModifiedId());
        evt.setTsLastModifiedTime(new Date());
        evt.setRequestId(cmd.getRequestId());
        evt.setRiskInfos(cmd.getRiskInfos());
        AggregateLifecycle.apply(evt);
    }


}
