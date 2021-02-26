package fund.investment.basic.inqresult.server.aggregate.status;


import fund.investment.basic.inqresult.api.enumeration.InquiryResultStatus;

public class CompletedInquiryResultState extends InquiryResultState {

    public CompletedInquiryResultState() {
        super(InquiryResultStatus.COMPLETED);
    }
}
