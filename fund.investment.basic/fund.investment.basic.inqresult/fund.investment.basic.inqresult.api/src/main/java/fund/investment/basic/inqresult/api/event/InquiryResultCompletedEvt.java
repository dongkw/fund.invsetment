package fund.investment.basic.inqresult.api.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InquiryResultCompletedEvt extends InquiryResultEvent {

    public InquiryResultCompletedEvt(String id, String skId, String skInquiry) {
        super( id, skId, skInquiry);
    }
}
