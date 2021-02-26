package fund.investment.basic.inqresult.server.aggregate.status;

import fund.investment.basic.inqresult.api.enumeration.InquiryResultStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CancelledInquiryResultState extends InquiryResultState {

    public CancelledInquiryResultState() {
        super(InquiryResultStatus.CANCELLED);
    }
}
