package fund.investment.basic.trade.api.command;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderFailedCmd extends OrderCommand {

    private List<RiskResultResponse> riskInfos;

}
