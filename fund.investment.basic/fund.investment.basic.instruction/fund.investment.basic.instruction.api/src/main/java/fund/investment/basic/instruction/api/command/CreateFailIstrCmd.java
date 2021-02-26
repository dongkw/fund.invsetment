package fund.investment.basic.instruction.api.command;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import fund.investment.basic.instruction.api.enumeration.TradeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFailIstrCmd extends InstructionCommand {

    private String failCode;

    private String failMsg;
    private List<RiskResultResponse> riskRiskInfos;


    public CreateFailIstrCmd(TradeType tradeType, String id, String skId, String skInstr) {
        super(tradeType, id, skId, skInstr);
    }
}
