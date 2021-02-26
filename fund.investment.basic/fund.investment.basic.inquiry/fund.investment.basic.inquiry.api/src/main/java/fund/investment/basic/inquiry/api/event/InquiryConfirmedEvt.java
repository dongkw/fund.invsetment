package fund.investment.basic.inquiry.api.event;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InquiryConfirmedEvt extends InquiryEvent {
    private String chInstrSource;
    private String chSourceKey;
    private String chSourceNo;

    public InquiryConfirmedEvt(String id, String skId, String skInquiry, String cInstrSource) {
        super(id, skId, skInquiry);
        this.chInstrSource = cInstrSource;
    }
}
