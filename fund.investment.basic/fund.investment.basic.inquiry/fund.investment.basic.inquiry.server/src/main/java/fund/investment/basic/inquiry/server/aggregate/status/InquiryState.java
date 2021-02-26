package fund.investment.basic.inquiry.server.aggregate.status;


import fund.investment.basic.inquiry.api.command.CancelConfInquiryCmd;
import fund.investment.basic.inquiry.api.command.CancelInquiryCmd;
import fund.investment.basic.inquiry.api.command.CreateConfirmInquiryCmd;
import fund.investment.basic.inquiry.api.command.CreateFailInquiryCmd;
import fund.investment.basic.inquiry.api.enumeration.InquiryStatus;
import fund.investment.basic.inquiry.server.aggregate.InquiryAggregate;

public class InquiryState {
    private InquiryStatus inquiryStatus;


    public InquiryState(InquiryStatus instructionStatus) {
        this.inquiryStatus = instructionStatus;
    }

    public void createConfirm(InquiryAggregate aggregate, CreateConfirmInquiryCmd confirmCmd) {
    }

    public void createFail(InquiryAggregate aggregate, CreateFailInquiryCmd createFailCmd) {
    }

    public void cancel(InquiryAggregate aggregate, CancelInquiryCmd cancelIstrCmd) {
    }

    public void cancelConfirm(InquiryAggregate aggregate, CancelConfInquiryCmd cancelConfIstrCmd) {
    }


}
