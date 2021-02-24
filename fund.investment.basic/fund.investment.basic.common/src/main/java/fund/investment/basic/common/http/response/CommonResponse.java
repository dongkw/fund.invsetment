package fund.investment.basic.common.http.response;

import fund.investment.common.http.response.risk.RiskResultDtoResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Data
public class CommonResponse implements Serializable {
    private List<RiskResultDtoResponse> riskInfos;
    private String code;
    private String message;
    private Boolean success;
}
