package fund.investment.basic.inqresult.server.aggregate.status;


import fund.investment.basic.inqresult.api.enumeration.InquiryResultStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreatedInquiryResultState extends InquiryResultState {

    public CreatedInquiryResultState() {
        super(InquiryResultStatus.CREATED);
    }




}
