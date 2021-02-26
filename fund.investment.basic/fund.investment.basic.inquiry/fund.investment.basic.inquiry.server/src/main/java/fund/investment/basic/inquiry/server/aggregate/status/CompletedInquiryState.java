package fund.investment.basic.inquiry.server.aggregate.status;


import fund.investment.basic.inquiry.api.enumeration.InquiryStatus;

public class CompletedInquiryState extends InquiryState {

    public CompletedInquiryState() {
        super(InquiryStatus.COMPLETED);
    }
}
