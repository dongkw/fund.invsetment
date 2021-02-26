package fund.investment.basic.inqresult.api.command;

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
public class CreateFailInquiryResultCmd extends InquiryResultCommand {

    private String failCode;

    private String failMsg;
    private List<RiskResultResponse> riskInfos;

    public CreateFailInquiryResultCmd(String id) {
        super(id);
    }
}
