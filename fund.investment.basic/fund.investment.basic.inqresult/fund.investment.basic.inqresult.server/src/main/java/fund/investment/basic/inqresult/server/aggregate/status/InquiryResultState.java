package fund.investment.basic.inqresult.server.aggregate.status;


import fund.investment.basic.inqresult.api.command.*;
import fund.investment.basic.inqresult.api.enumeration.InquiryResultStatus;
import fund.investment.basic.inqresult.server.aggregate.InquiryResultAggregate;

public class InquiryResultState {
    private InquiryResultStatus inquiryResultStatus;


    public InquiryResultState(InquiryResultStatus instructionStatus) {
        this.inquiryResultStatus = instructionStatus;
    }

    public void createConfirm(InquiryResultAggregate aggregate, CreateConfirmInquiryResultCmd cmd) {
    }

    public void createFail(InquiryResultAggregate aggregate, CreateFailInquiryResultCmd cmd) {
    }

    public void cancel(InquiryResultAggregate aggregate, CancelInquiryResultCmd cmd) {
    }

    public void cancelConfirm(InquiryResultAggregate aggregate, CancelConfInquiryResultCmd cmd) {
    }

    public void update(InquiryResultAggregate aggregate, UpdateInquiryResultCmd cmd) {
    }

    public void updateConfirm(InquiryResultAggregate aggregate, UpdateConfirmInquiryResultCmd cmd) {
    }

    public void updateFail(InquiryResultAggregate aggregate, UpdateFailInquiryResultCmd cmd) {
    }

    public void commit(InquiryResultAggregate aggregate, CommitInqResultCmd cmd) {
    }

    public void back(InquiryResultAggregate aggregate, BackInquiryResultCmd cmd) {
    }

    public void reject(InquiryResultAggregate aggregate, RejectInquiryResultCmd cmd) {
    }

    public void finish(InquiryResultAggregate aggregate, FinishInquiryResultCmd cmd) {
    }
}
