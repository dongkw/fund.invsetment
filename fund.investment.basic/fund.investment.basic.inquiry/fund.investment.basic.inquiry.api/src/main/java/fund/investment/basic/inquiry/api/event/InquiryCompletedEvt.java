package fund.investment.basic.inquiry.api.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InquiryCompletedEvt extends InquiryEvent {

    public InquiryCompletedEvt(String id, String skId, String skInquiry) {
        super( id, skId, skInquiry);
    }
}
