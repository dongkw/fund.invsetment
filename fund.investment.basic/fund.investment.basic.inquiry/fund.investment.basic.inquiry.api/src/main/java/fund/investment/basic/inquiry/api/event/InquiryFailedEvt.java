package fund.investment.basic.inquiry.api.event;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryFailedEvt extends InquiryEvent {

    private String failCode;

    private String failMsg;
    private List<RiskResultResponse> riskRiskInfos;

    public InquiryFailedEvt(String id, String skId, String skInquiry) {
        super(id, skId, skInquiry);
    }
}
