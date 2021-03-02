package fund.investment.basic.instruction.api.command;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateFailIstrCmd extends InstructionCommand {

    private List<RiskResultResponse> riskRiskInfos;


}
