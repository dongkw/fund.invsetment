package fund.investment.basic.trade.api.valueobject;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Data;

import java.util.List;

/**
 * @Author dongkw
 * @Date 2021/3/3、10:03 上午
 **/
@Data
public class FailTrade {


    private List<RiskResultResponse> riskInfos;

}
