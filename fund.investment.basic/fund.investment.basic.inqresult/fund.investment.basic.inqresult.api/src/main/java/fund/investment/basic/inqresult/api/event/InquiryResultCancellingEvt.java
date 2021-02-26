package fund.investment.basic.inqresult.api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryResultCancellingEvt extends InquiryResultEvent {

    private String unitId;

    private String chMemo;

    private String securityCode;

    private String cancelType;

    private String cancelMsg;

    public InquiryResultCancellingEvt(String id, String skId, String skInquiry) {
        super(id, skId, skInquiry);
    }
}
