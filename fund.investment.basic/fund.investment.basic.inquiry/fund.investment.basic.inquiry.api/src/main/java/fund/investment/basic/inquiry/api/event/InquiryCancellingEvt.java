package fund.investment.basic.inquiry.api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryCancellingEvt extends InquiryEvent {

    private String unitId;

    private String chSourceNo;

    private String chMemo;

    private String cancelType;

    private String cancelMsg;

    public InquiryCancellingEvt(String id, String skId, String skInquiry) {
        super(id, skId, skInquiry);
    }
}
