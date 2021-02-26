package fund.investment.basic.inqresult.api.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InquiryResultConfirmedEvt extends InquiryResultEvent {

    public InquiryResultConfirmedEvt(String id, String skId, String skInquiry) {
        super(id, skId, skInquiry);
    }
}
