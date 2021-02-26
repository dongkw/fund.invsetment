package fund.investment.basic.inqresult.server.aggregate.status;


import fund.investment.basic.inqresult.api.enumeration.InquiryResultStatus;

public class FailedInquiryResultState extends InquiryResultState {

    public FailedInquiryResultState() {
        super(InquiryResultStatus.FAILED);
    }
}
