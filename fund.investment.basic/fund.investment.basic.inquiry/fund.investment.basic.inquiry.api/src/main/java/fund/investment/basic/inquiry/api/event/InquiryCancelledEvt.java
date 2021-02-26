package fund.investment.basic.inquiry.api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryCancelledEvt extends InquiryEvent {

    private String unitId;

    private String description;

    private String securityCode;

    private String cancelType;

    private String cancelMsg;

    public InquiryCancelledEvt(String id, String skId, String skInquiry) {
        super(id, skId, skInquiry);
    }
}
