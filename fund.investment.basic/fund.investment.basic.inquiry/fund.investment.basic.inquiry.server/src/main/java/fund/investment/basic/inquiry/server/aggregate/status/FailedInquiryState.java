package fund.investment.basic.inquiry.server.aggregate.status;


import fund.investment.basic.inquiry.api.enumeration.InquiryStatus;

public class FailedInquiryState extends InquiryState {

    public FailedInquiryState() {
        super(InquiryStatus.FAILED);
    }
}
