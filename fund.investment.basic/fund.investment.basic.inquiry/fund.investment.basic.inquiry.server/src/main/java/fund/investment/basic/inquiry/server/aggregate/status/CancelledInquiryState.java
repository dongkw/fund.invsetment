package fund.investment.basic.inquiry.server.aggregate.status;

import fund.investment.basic.inquiry.api.enumeration.InquiryStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancelledInquiryState extends InquiryState {

    public CancelledInquiryState() {
        super(InquiryStatus.CANCELLED);
    }
}
