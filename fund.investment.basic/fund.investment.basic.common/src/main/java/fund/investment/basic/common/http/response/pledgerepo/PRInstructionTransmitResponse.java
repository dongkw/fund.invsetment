package fund.investment.basic.common.http.response.pledgerepo;

import lombok.Getter;
import lombok.Setter;

/**
 * 指令下达返回
 */
@Getter
@Setter
public class PRInstructionTransmitResponse extends ResultEntity{
    private TradeInvestResponse invest;
}
