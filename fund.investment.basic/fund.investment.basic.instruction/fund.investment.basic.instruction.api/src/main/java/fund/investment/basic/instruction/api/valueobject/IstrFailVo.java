package fund.investment.basic.instruction.api.valueobject;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Data;

import java.util.List;

/**
 * @Author dongkw
 * @Date 2021/2/26、3:35 下午
 **/
@Data
public class IstrFailVo {

    private List<RiskResultResponse> riskRiskInfos;

}
