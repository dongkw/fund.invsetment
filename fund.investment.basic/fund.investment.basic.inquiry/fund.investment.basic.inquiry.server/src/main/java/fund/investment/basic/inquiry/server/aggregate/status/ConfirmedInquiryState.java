package fund.investment.basic.inquiry.server.aggregate.status;

import fund.investment.basic.inquiry.api.enumeration.InquiryStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfirmedInquiryState extends InquiryState {

    public ConfirmedInquiryState() {
        super(InquiryStatus.CONFIRMED);
    }


}
