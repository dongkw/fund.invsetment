package fund.investment.basic.instruction.api.command;

import fund.investment.basic.common.http.response.risk.RiskResultResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Author dongkw
 * @Date 2020/10/10、9:29 上午
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RollbackCancelIstrCmd extends InstructionCommand {

    private String cancelType;

    private String cancelMsg;

    private List<RiskResultResponse> riskInfos;


}
